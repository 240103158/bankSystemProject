package ideaprojects.banksystembackend.controller;


import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;


    @Autowired
    public AuthController(BCryptPasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login-page";
    }

    @GetMapping("/register")
    public String getRegistrationPage(){
        return "registration-page";
    }



    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password){

        String encodedPassword = encoder.encode(password);
        String result = userService.register(new User(firstName, lastName, email, encodedPassword));

        if (!result.equals("User registered successfully")) {
            return "redirect:/api/auth/register?error=true";
        }

        return "redirect:/api/auth/login";
    }

}
