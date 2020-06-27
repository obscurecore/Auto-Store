package ru.ruslan.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.models.Role;
import ru.ruslan.models.State;
import ru.ruslan.models.User;
import ru.ruslan.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final Mail mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean activateUser(String code) {
        Optional<User> userOptional = userRepository.findUsersByActivationCode(code);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActivationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public EmailDto signUp(SignUpDto form) {

      //  String link = "http://localhost:8080/signUp/confirmation/" + UUID.randomUUID().toString();

        Optional<User> userOptional = userRepository.findUsersByEmail(form.getEmail());
        if (userOptional.isPresent()) {
            return null;
        }

        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .state(State.NOT_CONFIRMED)
                .roles(Collections.singleton(Role.USER))
                .activationCode(UUID.randomUUID().toString())
                .build();

        String message = String.format(
                "Hello!, %s! \n" +
                        "Welcome to Store. Please visit next link to activate your account: http://localhost:8080/signUo/confirmation/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        userRepository.save(user);
        mailSender.send(user.getEmail(), "Activation code", message);
        System.err.println("==========RETURN POINT==========");

        return EmailDto.builder()
                .body(message)
                .to(user.getEmail())
                .templateName("email_confirmation")
                .build();
    }
}
