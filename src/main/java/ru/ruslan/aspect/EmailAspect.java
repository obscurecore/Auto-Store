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
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username: Auto-Store CLUB}")
    private String username;

    @AfterReturning(value = "execution(* ru.ruslan.service.contract.SignUpService.signUp(*))", returning = "emailDto")
    public void signUpAdvice(EmailDto emailDto) {

        String message = String.format(
                "Hello!, \n" +
                        "Welcome to Store. Please visit next link to activate your account: http://localhost:8080/signUo/confirmation/"
                // emailDto.getUsername(),
                // emailDto.getSecret()
        );

        var msg = new SimpleMailMessage();
        msg.setFrom(username);
        //msg.setTo(emailDto.getTo());
        msg.setTo("");

        msg.setSubject("activation");
        msg.setText(message);
        javaMailSender.send(msg);
    }
}