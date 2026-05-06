package ideaprojects.banksystembackend.service;

import ideaprojects.banksystembackend.Entity.CustomOAuth2User;
import ideaprojects.banksystembackend.Entity.User;
import ideaprojects.banksystembackend.Entity.UserRole;
import ideaprojects.banksystembackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String[] parts = (name != null ? name : "Unknown").split(" ", 2);
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User(
                    firstName,
                    lastName,
                    email,
                    encoder.encode(UUID.randomUUID().toString()),
                    UserRole.USER_STANDARD, LocalDateTime.now());

            return userRepository.save(newUser);
        });

        return new CustomOAuth2User(oAuth2User, user);

    }

}
