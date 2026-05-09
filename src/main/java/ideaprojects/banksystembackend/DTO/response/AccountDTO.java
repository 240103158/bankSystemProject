package ideaprojects.banksystembackend.DTO.response;

import ideaprojects.banksystembackend.Entity.Account;
import ideaprojects.banksystembackend.Entity.AccountStatus;
import ideaprojects.banksystembackend.Entity.User;

public class AccountDTO {
    private String accountNumber;
    private double balance;
    private String currency;
    private String accountStatus;
    private String ownerName;

    public AccountDTO(String accountNumber, String currency, double balance, String accountStatus, String ownerName) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.accountStatus = accountStatus;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public Account toEntity(User user) {
        Account account = new Account(
                accountNumber,
                balance,
                user,
                AccountStatus.valueOf(accountStatus)
        );
        account.setCurrency(currency);
        return account;
    }
}
