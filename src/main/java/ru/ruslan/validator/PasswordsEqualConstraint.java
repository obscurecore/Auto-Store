package ru.ruslan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)

public @interface PasswordsEqualConstraint {
    String message() default "{error equil}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}