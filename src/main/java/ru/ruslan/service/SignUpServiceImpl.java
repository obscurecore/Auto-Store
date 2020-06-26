package ru.ruslan.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
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

    private Mail mailSender;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

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
    public boolean signUp(SignUpDto form) {

        Optional<User> userOptional = userRepository.findUsersByEmail(form.getEmail());
        if (userOptional.isPresent()) {
            return false;
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
                        "Welcome to Store. Please visit next link to activate your account: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        userRepository.save(user);
        mailSender.send(user.getEmail(), "Activation code", message);
        return true;
    }
}
