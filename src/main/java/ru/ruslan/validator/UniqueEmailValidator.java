package ru.ruslan.validator;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ruslan.repository.UserRepository;
import ru.ruslan.validator.contract.UniqueEmail;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class is implementing ConstraintValidator<A extends Annotation,T> check if the email is unique in DB
 * @author ruslan
 */
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.existsUserByEmail(value);

    }
}