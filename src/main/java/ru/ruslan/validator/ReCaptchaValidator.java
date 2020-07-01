package ru.ruslan.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.ruslan.dto.ReCaptchaDto;
import ru.ruslan.validator.contract.ReCaptcha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;


public class ReCaptchaValidator implements ConstraintValidator<ReCaptcha, String> {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    RestTemplate restTemplate;
    @Value("${recaptcha.secret}")
    private String secret;

    @Override
    public void initialize(ReCaptcha constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        String url = String.format(CAPTCHA_URL, secret, value);
        return restTemplate.postForObject(url, Collections.emptyList(), ReCaptchaDto.class).isSuccess();
    }

}
