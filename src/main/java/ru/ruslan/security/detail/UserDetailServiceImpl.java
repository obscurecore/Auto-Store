package ru.ruslan.security.detail;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ruslan.model.User;
import ru.ruslan.repository.UserRepository;

import java.util.Optional;

/**
 * Custom realization to get Authentication
 */
@AllArgsConstructor
@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUsersByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        }
         throw new UsernameNotFoundException(messageSource.getMessage("error.user.not_found",null, LocaleContextHolder.getLocale()));
    }
}
