package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.Account;
import ideaprojects.banksystembackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUser(User user);
    Account findByUserId(int userId);
}
