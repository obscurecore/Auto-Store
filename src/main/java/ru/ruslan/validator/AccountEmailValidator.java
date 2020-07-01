package ru.ruslan.validator;

import lombok.AllArgsConstructor;
import ru.ruslan.aspect.EmailAspect;
import ru.ruslan.dto.EmailDto;
import ru.ruslan.model.State;
import ru.ruslan.model.User;
import ru.ruslan.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class AccountEmailValidator implements ConstraintValidator<AccountEmail, String> {
    UserRepository userRepository;
    EmailAspect emailAspect;

    @Override
    public void initialize(AccountEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String link, ConstraintValidatorContext cxt) {

        cxt.disableDefaultConstraintViolation();
        Optional<User> userOptional = userRepository.findUserByVerificationToken_Token(link);
        if (!userOptional.isPresent()) {
            cxt.buildConstraintViolationWithTemplate("{error.invalid_link.mail}").addConstraintViolation();
            return false;
        } else {
            var user = userOptional.get();
            var cal = Calendar.getInstance();
            if ((user.getVerificationToken().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                emailAspect.signUpAdvice(EmailDto.builder()
                        .username(user.getUsername())
                        .secret(UUID.randomUUID().toString())
                        .to(user.getEmail())
                        .templateName("Reactivation account")
                        .build());

                cxt.buildConstraintViolationWithTemplate("{error.expired_link.mail}").addConstraintViolation();
                return false;
            }
            user.setState(State.CONFIRMED);
            userRepository.save(user);

            return true;
        }
    }

}