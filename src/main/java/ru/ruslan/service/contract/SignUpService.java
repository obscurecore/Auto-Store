package ru.ruslan.service.contract;

import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;

import java.security.cert.CertificateExpiredException;

public interface SignUpService {
    EmailDto signUp(SignUpDto s);
}