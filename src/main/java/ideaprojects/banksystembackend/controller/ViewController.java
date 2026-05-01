package ideaprojects.banksystembackend.controller;

import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ViewController {

    private final UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getStartPage(Principal principal) {
        if (principal != null) {
            return "redirect:/api/accounts";
        }
        return "start-page";
    }

    @GetMapping("/api/accounts")
    public String getDashboard(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("account", user.getAccount());
            model.addAttribute("cards", user.getCards());
            if (user.getAccount() != null) {
                model.addAttribute("transactions", user.getAccount().getTransactions());
            }
        }
        return "dashboard";
    }
}
