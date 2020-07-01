package ru.ruslan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ruslan.model.State;
import ru.ruslan.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByEmail(String email);

    Optional<User> findUserByVerificationToken_Token(String verificationToken_token);

    boolean existsUserByEmail(String email);


}
