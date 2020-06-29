package ru.ruslan.dto;

import lombok.Data;
import ru.ruslan.validator.PasswordsEqualConstraint;
import ru.ruslan.validator.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@PasswordsEqualConstraint(message = "passwords are not equal")
public class SignUpDto {
    private String username;

    @NotBlank(message = "Please, fill the email")
    @Email(message = "Email is not correct")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min=5, max=45, message = "5:45")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String passwordRepeat;
}