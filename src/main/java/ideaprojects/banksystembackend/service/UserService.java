package ideaprojects.banksystembackend.service;


import ideaprojects.banksystembackend.DTO.request.RegisterRequest;
import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.Entity.UserRole;
import ideaprojects.banksystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  String register(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return "User with email " + user.getEmail() + " already exists";
        }
        userRepository.save(user);
        return "User registered successfully";
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));
    }

    public User getUserById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

//    public String updateUser(UUID id, User user){
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
//        user.setEmail(user.getEmail());
//        user.setPassword(user.getPassword());
//        userRepository.save(user);
//
//        return "User updated successfully";
//    }

    public String changeRole(UUID id, String role){
        User user = getUserById(id);
        if(role == null || role.isEmpty()){
            return "Role cannot be null or empty";
        }

        if(role .equals(user.getRole().name())){
            return "User already has the role " + role;
        }

        try {
            user.setRole(UserRole.valueOf(role));
        } catch (IllegalArgumentException e) {
            return "Invalid role: " + role;
        }
        userRepository.save(user);
        return "User role updated successfully to " + role;

    }

}

