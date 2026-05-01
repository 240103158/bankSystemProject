package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
