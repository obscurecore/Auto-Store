package ru.ruslan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ruslan.repository.UserRepository;
import ru.ruslan.validator.contract.UniqueEmail;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class is implementing ConstraintValidator<A extends Annotation,T>
 * interface that has only one method — isValid(String value, ConstraintValidatorContext context)
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.existsUserByEmail(value);

    }
}