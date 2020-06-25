package ru.ruslan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ruslan.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByEmail(String email);
}
