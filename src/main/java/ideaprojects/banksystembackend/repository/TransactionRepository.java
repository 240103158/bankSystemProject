package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.Account;
import ideaprojects.banksystembackend.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Transaction findByAccount(Account Account);
    Transaction findByAccountId(int accountId);
    Transaction findByAccountOrderByDateDesc(Account account);
}
