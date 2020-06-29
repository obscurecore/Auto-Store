package ru.ruslan.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ruslan.aspect.EmailAspect;
import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.exception.VerificationTokenExpiredException;
import ru.ruslan.model.Role;
import ru.ruslan.model.State;
import ru.ruslan.model.User;
import ru.ruslan.model.VerificationToken;
import ru.ruslan.repository.UserRepository;
import ru.ruslan.service.contract.SignUpService;

import java.util.*;

@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EmailAspect emailAspect;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public void confirm(String link) {
        Optional<User> userOptional = userRepository.findUserByVerificationToken_Token(link);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Invalid Link");
        } else {
            var user = userOptional.get();
            var cal = Calendar.getInstance();
            if ((user.getVerificationToken().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                emailAspect.signUpAdvice(EmailDto.builder()
                        .username(user.getUsername())
                        .secret(UUID.randomUUID().toString())
                        .to(user.getEmail())
                        .templateName("Recheck email")
                        .build());

                throw new VerificationTokenExpiredException();
            }
            user.setState(State.CONFIRMED);
            userRepository.save(user);
        }
    }

    @Override
    public EmailDto signUp(SignUpDto form) {

        if (form.getUsername() == null) {
            form.setUsername("User" + new Random().nextInt(99999));
        }
        var uuid = UUID.randomUUID().toString();

        var verificationToken = new VerificationToken();
        verificationToken.setToken(uuid);

        var user = User.builder()
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
