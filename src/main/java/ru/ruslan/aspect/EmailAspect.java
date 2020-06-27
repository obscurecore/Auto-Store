package ru.ruslan.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.EmailDto;

@Component
@Aspect
public class EmailAspect {
    @Value("${spring.mail.username: Auto-Store CLUB}")
    private String username;
    @Autowired
    JavaMailSender javaMailSender;

    @AfterReturning(value = "execution(* ru.ruslan.service.interf.SignUpService.signUp(*))", returning = "emailDto")
    public void signUpAdvice(EmailDto emailDto) {

        String message = String.format(
                "Hello!,  %s! \n" +
                        "Welcome to Store. Please visit next link to activate your account: http://localhost:8080/signUo/confirmation/%s",
                emailDto.getUsername(),
                emailDto.getSecret()
        );


        var msg = new SimpleMailMessage();

        msg.setFrom(username);
        msg.setTo(emailDto.getTo());
        msg.setSubject(emailDto.getTemplateName());
        msg.setText(message);
        javaMailSender.send(msg);
    }
}