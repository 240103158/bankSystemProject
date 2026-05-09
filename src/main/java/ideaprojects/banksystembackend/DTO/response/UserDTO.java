package ideaprojects.banksystembackend.DTO.response;

import ideaprojects.banksystembackend.Entity.User;

import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO(UUID id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public User toEntity(User user) {
        return new User(
                firstName,
                lastName,
                email,
                user.getPassword(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
