package ideaprojects.banksystembackend.DTO.request;

import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.Entity.UserRole;

import java.time.LocalDateTime;

public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole userRole;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = UserRole.USER_STANDARD; // by default

    }

    public User toEntity() {
        return new User(email, password, firstName, lastName, userRole, LocalDateTime.now());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


}
