package ru.ruslan.validator.contract;

import ru.ruslan.validator.AccountEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountEmailValidator.class)

public @interface AccountEmail {
    String message() default "Denied";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}