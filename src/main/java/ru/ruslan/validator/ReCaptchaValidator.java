package ru.ruslan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.ruslan.dto.ReCaptchaDto;
import ru.ruslan.validator.contract.ReCaptcha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.Objects;

/**
 * Class is implementing ConstraintValidator<A extends Annotation,T> check status of response for captcha
 *
 * @author ruslan
 */
public class ReCaptchaValidator implements ConstraintValidator<ReCaptcha, String> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @Value("${recaptcha.url}")
    private String CAPTCHA_URL;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        var url = String.format(CAPTCHA_URL, secret, value);
        return Objects.requireNonNull(restTemplate.postForObject(url, Collections.emptyList(), ReCaptchaDto.class)).isSuccess();
    }

}
