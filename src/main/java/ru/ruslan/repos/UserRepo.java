package ru.ruslan.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ruslan.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
