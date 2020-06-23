package ru.ruslan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ruslan.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
