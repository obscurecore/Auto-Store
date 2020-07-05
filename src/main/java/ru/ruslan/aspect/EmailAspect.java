package ru.ruslan.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.EmailDto;


/**
 * Email aspect for confirmation account.
 * @author ruslan
 */
@Aspect
@Component
@AllArgsConstructor
public class EmailAspect {

    JavaMailSender javaMailSender;

    /**
     * Send email with verification code - UUID
     *
     * @param emailDto - dto
     */
    @AfterReturning(value = "execution(* ru.ruslan.service.contract.SignUpService.signUp(*))", returning = "emailDto")
    public void signUpAdvice(EmailDto emailDto) {

        var message = String.format(
                "Hello, %s!\n" +
                        "Welcome to Store. Please visit next link to activate your account:" +
                        "http://localhost:8080/signUo/confirmation/%s!\n" +
                        "Valid for 24 hours",
                emailDto.getUsername(),
                emailDto.getSecret()
        );

        var msg = new SimpleMailMessage();
        msg.setTo(emailDto.getTo());
        msg.setText(message);
        msg.setSubject(emailDto.getTemplateName());
        javaMailSender.send(msg);
    }

}