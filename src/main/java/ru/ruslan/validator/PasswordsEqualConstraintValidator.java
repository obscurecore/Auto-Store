package ru.ruslan.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.ruslan.dto.SignUpDto;

public class PasswordsEqualConstraintValidator implements
    ConstraintValidator<PasswordsEqualConstraint, Object> {

@Override
public void initialize(PasswordsEqualConstraint arg0) {
}

@Override
public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
    var user = (SignUpDto) candidate;
    return user.getPassword().equals(user.getPasswordRepeat());
}
}