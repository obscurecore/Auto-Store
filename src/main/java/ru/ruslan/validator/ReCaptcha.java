package ru.ruslan.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ReCaptchaValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReCaptcha {
    public String message() default "Are you bot?)";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}