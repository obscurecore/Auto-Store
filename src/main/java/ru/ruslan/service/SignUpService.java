package ru.ruslan.service;

import ru.ruslan.dto.SignUpDto;

public interface SignUpService {
    boolean activateUser(String code);

    boolean signUp(SignUpDto form);
}