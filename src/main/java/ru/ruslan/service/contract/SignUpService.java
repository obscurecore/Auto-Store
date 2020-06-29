package ru.ruslan.service.contract;

import ru.ruslan.dto.EmailDto;
import ru.ruslan.dto.SignUpDto;

import java.security.cert.CertificateExpiredException;

public interface SignUpService {
    void confirm(String code) throws CertificateExpiredException;

    EmailDto signUp(SignUpDto s);
}