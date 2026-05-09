package ideaprojects.banksystembackend.controller;

import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.Entity.UserRole;
import ideaprojects.banksystembackend.repository.TransactionRepository;
import ideaprojects.banksystembackend.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ViewController {

    private final UserService userService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public ViewController(UserService userService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/")
    public String getStartPage(Principal principal) {
        if (principal != null) {
            return "redirect:/api/accounts";
        }
        return "start-page";
    }

    @GetMapping("/api/transfer")
    public String getTransferPage(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("account", user.getAccount());
            model.addAttribute("userId", user.getId());
        }
        return "transfer";
    }

    @GetMapping("/api/test")
    public String getApiTest() {
        UserRole userRole = userService.getCurrentUser().getRole();

        if(userRole == UserRole.ADMIN_ROLE){
            return "api-test";
        } else {
            return "redirect:/api/accounts";
        }
    }

    @GetMapping("/api/accounts")
    public String getDashboard(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("account", user.getAccount());
            model.addAttribute("cards", user.getCards());
            if (user.getAccount() != null) {
                model.addAttribute("transactions",
                        transactionRepository.findBySenderAccountId(user.getAccount().getId()));
            }
        }
        return "dashboard";
    }
}
