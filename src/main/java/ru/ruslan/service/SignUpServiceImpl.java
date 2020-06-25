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

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto form) {
        Set<Role> set = new HashSet();
        set.add(Role.USER);
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .state(State.NOT_CONFIRMED)
                .roles(set)
                .build();

        userRepository.save(user);
    }
}
