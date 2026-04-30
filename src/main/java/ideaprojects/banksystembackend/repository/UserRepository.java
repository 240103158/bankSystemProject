package ideaprojects.banksystembackend.repository;

import ideaprojects.banksystembackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
