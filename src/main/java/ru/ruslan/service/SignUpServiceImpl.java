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
import ru.ruslan.service.contract.SignUpService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
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
    */
    @Transactional
    @Override
    public EmailDto signUp(SignUpDto form) {

        if (form.getUsername() == null) {
            form.setUsername("User" + new Random().nextInt(99999));
        }
        String uuid = UUID.randomUUID().toString();

        var verificationToken = new VerificationToken();
        verificationToken.setToken(uuid);

        User user = User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .state(State.NOT_CONFIRMED)
                .roles(Collections.singleton(Role.USER))
                .verificationToken(verificationToken)
                .build();
        userRepository.save(user);

        return EmailDto.builder()
                .username(form.getUsername())
                .secret(uuid)
                .to(user.getEmail())
                .templateName("Email Confirmation")
                .build();
    }
}
