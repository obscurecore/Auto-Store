package ru.ruslan.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}