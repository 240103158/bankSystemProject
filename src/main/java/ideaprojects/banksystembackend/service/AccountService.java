package ideaprojects.banksystembackend.service;

import ideaprojects.banksystembackend.DTO.request.TransferRequest;
import ideaprojects.banksystembackend.DTO.response.AccountDTO;
import ideaprojects.banksystembackend.DTO.response.TransactionDTO;
import ideaprojects.banksystembackend.Entity.Account;
import ideaprojects.banksystembackend.Entity.Transaction;
import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.repository.AccountRepository;
import ideaprojects.banksystembackend.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static ideaprojects.banksystembackend.Entity.AccountStatus.ACCOUNT_STATUS_ACTIVE;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final Random random = new Random();

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountDTO findByUserId(UUID id) {
        Account account = accountRepository.findByUserId(id).orElseThrow(() ->
                new RuntimeException("Account not found for user id: " + id));
        return account.toDto();
    }

    public AccountDTO createAccount(User user, String currency) {
        Account newAcc = new Account(
                generateUniqueAccountNumber(),
                0.0,
                user,
                ACCOUNT_STATUS_ACTIVE
        );
        newAcc.setCurrency(currency);
        accountRepository.save(newAcc);
        return newAcc.toDto();
    }

    public AccountDTO getAccountByUser(User user) {
        Account account = accountRepository.findByUser(user).orElseThrow(() ->
                new RuntimeException("Account not found for user: " + user.getFullName()));
        return account.toDto();
    }

    public AccountDTO deposit(TransferRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getSenderAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found: " + request.getSenderAccountNumber()));

        if (request.getAmount() <= 0 || account.getAccountStatus() != ACCOUNT_STATUS_ACTIVE) {
            throw new RuntimeException("Invalid deposit amount or account is not active");
        }

        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                request.getTransactionType(),
                request.getAmount(),
                request.getCurrency(),
                request.getDate(),
                request.getDescription(),
                account.getId(),
                null
        ));

        return account.toDto();
    }

    public AccountDTO withdraw(TransferRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getSenderAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found: " + request.getSenderAccountNumber()));

        if (request.getAmount() <= 0 || account.getBalance() < request.getAmount()
                || account.getAccountStatus() != ACCOUNT_STATUS_ACTIVE) {
            throw new RuntimeException("Invalid withdrawal amount, insufficient balance, or account is not active");
        }

        account.setBalance(account.getBalance() - request.getAmount());
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                request.getTransactionType(),
                request.getAmount(),
                request.getCurrency(),
                request.getDate(),
                request.getDescription(),
                account.getId(),
                null
        ));

        return account.toDto();
    }

    public void transfer(TransferRequest request) {
        Account senderAccount = accountRepository.findByAccountNumber(request.getSenderAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender account not found: " + request.getSenderAccountNumber()));

        Account receiverAccount = accountRepository.findByAccountNumber(request.getReceiverAccountNumber())
                .orElseThrow(() -> new RuntimeException("Receiver account not found: " + request.getReceiverAccountNumber()));

        if (request.getAmount() <= 0 || senderAccount.getBalance() < request.getAmount()
                || senderAccount.getAccountStatus() != ACCOUNT_STATUS_ACTIVE
                || receiverAccount.getAccountStatus() != ACCOUNT_STATUS_ACTIVE) {
            throw new RuntimeException("Invalid transfer amount, insufficient balance, or account is not active");
        }



        senderAccount.setBalance(senderAccount.getBalance() - request.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() + request.getAmount());

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        transactionRepository.save(new Transaction(
                request.getTransactionType(),
                request.getAmount(),
                request.getCurrency(),
                request.getDate(),
                request.getDescription(),
                senderAccount.getId(),
                receiverAccount.getId()
        ));
    }

    public List<TransactionDTO> getTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));

        List<Transaction> transactions = transactionRepository.findBySenderAccountId(account.getId());

        if (transactions.isEmpty()) {
            throw new RuntimeException("No transactions found for account: " + accountNumber);
        }

        return transactions.stream().map(Transaction::toDto).toList();
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        } while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    private String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
