package ru.ruslan.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.ruslan.validator.PasswordsEqualConstraint;
import ru.ruslan.validator.ReCaptcha;
import ru.ruslan.validator.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Component
@PasswordsEqualConstraint(message = "passwords are not equal")
public class SignUpDto {

    @ReCaptcha
    @JsonAlias("g-recaptcha-response")
    String captchaResponse;

    private String username;

    @NotBlank(message = "Please, fill the email")
    @Email(message = "{errors.incorrect.email}")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 45, message = "5:45")
    private String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String passwordRepeat;
}