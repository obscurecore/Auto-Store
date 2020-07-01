package ru.ruslan.validator;

import ru.ruslan.dto.SignUpDto;
import ru.ruslan.validator.contract.PasswordsEqualConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        var user = (SignUpDto) candidate;
        return user.getPassword().equals(user.getPasswordRepeat());
    }
}