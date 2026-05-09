package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.Account;
import ideaprojects.banksystembackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUser(User user);
    Optional<Account> findByUserId(UUID id);
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
}
