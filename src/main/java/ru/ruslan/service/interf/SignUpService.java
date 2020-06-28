package ru.ruslan.service.interf;

import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.model.User;

public interface SignUpService {
   // boolean activateUser(String code);

    EmailDto signUp(SignUpDto s);
}