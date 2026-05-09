package ideaprojects.banksystembackend.controller;

import ideaprojects.banksystembackend.DTO.request.TransferRequest;
import ideaprojects.banksystembackend.DTO.response.AccountDTO;
import ideaprojects.banksystembackend.DTO.response.TransactionDTO;
import ideaprojects.banksystembackend.service.AccountService;
import ideaprojects.banksystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public AccountDTO getAccounts() {
        return accountService.getAccountByUser(userService.getCurrentUser());
    }

    @PostMapping("/deposit")
    public AccountDTO deposit(@RequestBody TransferRequest transferRequest) {
        transferRequest.setSenderAccountNumber(currentUserAccountNumber());
        return accountService.deposit(transferRequest);
    }

    @PostMapping("/withdraw")
    public AccountDTO withdraw(@RequestBody TransferRequest transferRequest) {
        transferRequest.setSenderAccountNumber(currentUserAccountNumber());
        return accountService.withdraw(transferRequest);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transferRequest) {
        transferRequest.setSenderAccountNumber(currentUserAccountNumber());
        accountService.transfer(transferRequest);
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions() {
        return accountService.getTransactions(currentUserAccountNumber());
    }

    private String currentUserAccountNumber() {
        return accountService.getAccountByUser(userService.getCurrentUser()).getAccountNumber();
    }
}
