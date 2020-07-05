package ru.ruslan.validator;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.ruslan.aspect.EmailAspect;
import ru.ruslan.dto.EmailDto;
import ru.ruslan.model.State;
import ru.ruslan.model.User;
import ru.ruslan.repository.UserRepository;
import ru.ruslan.validator.contract.AccountEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
/**
 * Class is implementing ConstraintValidator<A extends Annotation,T> check confirmation link
 * @author ruslan
 */
@AllArgsConstructor
public class AccountEmailValidator implements ConstraintValidator<AccountEmail, String> {
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final EmailAspect emailAspect;

    @Override
    public void initialize(AccountEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String link, ConstraintValidatorContext cxt) {

        cxt.disableDefaultConstraintViolation();
        Optional<User> userOptional = userRepository.findUserByVerificationToken_Token(link);
        if (userOptional.isEmpty()) {
            cxt.buildConstraintViolationWithTemplate("{error.email.invalid_link}").addConstraintViolation();
            return false;
        } else {
            var user = userOptional.get();
            var cal = Calendar.getInstance();
            if ((user.getVerificationToken().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                emailAspect.signUpAdvice(EmailDto.builder()
                        .username(user.getUsername())
                        .secret(UUID.randomUUID().toString())
                        .to(user.getEmail())
                        .templateName(messageSource.getMessage("email.reactivation", null, LocaleContextHolder.getLocale()))
                        .build());

                cxt.buildConstraintViolationWithTemplate("{error.email.expired_link}").addConstraintViolation();
                return false;
            }
            user.setState(State.CONFIRMED);
            userRepository.save(user);

            return true;
        }
    }

}