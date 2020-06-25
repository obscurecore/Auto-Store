package ru.ruslan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.models.Role;
import ru.ruslan.models.User;
import ru.ruslan.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto form) {
        Set<Role> set = new HashSet();
        set.add(Role.USER);
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getName())
                .roles(set)
                .build();

        userRepository.save(user);
    }
}
