package ru.ruslan.service;

import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;

public interface SignUpService {
    boolean activateUser(String code);

    EmailDto signUp(SignUpDto s);
}