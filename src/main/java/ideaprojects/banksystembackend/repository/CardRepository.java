package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.Card;
import ideaprojects.banksystembackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Card findByUser(User user);
        Card findByUserId(int userId);
}
