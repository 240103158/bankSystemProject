package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.Card;
import ideaprojects.banksystembackend.Entity.Transaction;
import ideaprojects.banksystembackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByOwner(User owner);
}
