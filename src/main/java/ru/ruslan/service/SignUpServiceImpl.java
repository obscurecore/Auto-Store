package ru.ruslan.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.model.Role;
import ru.ruslan.model.State;
import ru.ruslan.model.User;
import ru.ruslan.model.VerificationToken;
import ru.ruslan.repository.UserRepository;
import ru.ruslan.service.interf.SignUpService;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

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

        String uuid = UUID.randomUUID().toString();

        Optional<User> userOptional = userRepository.findUsersByEmail(form.getEmail());
        if (userOptional.isPresent()) {
            return null;
        }

        var verificationToken = new VerificationToken();
        verificationToken.setToken(uuid);
        var user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .state(State.NOT_CONFIRMED)
                .roles(Collections.singleton(Role.USER))
                .activationCode(UUID.randomUUID().toString())
                .verificationToken(verificationToken)
                .build();
        userRepository.save(user);

        return EmailDto.builder()
                .username(user.getUsername())
                .secret(uuid)
                .to(user.getEmail())
                .templateName("email_confirmation")
                .build();
    }
}
