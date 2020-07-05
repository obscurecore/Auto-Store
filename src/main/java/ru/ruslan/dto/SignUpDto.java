package ru.ruslan.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.stereotype.Component;
import ru.ruslan.validator.contract.PasswordsEqualConstraint;
import ru.ruslan.validator.contract.ReCaptcha;
import ru.ruslan.validator.contract.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The dto to sign up
 * @author ruslan
 */
@Data
@Component
@PasswordsEqualConstraint
public class SignUpDto {

    @ReCaptcha
    @JsonAlias("g-recaptcha-response")
    String captchaResponse;

    private String username;

    @NotBlank(message = "{error.not_blank.field}")
    @Email(message = "{errors.incorrect.email}")
    @UniqueEmail
    private String email;

    @NotBlank(message = "{error.not_blank.field}")
    @Size(min = 5, message = "{error.size.password}")
    private String password;

    @NotBlank(message = "{error.not_blank.field}")
    private String passwordRepeat;
}