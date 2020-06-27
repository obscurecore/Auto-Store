package ru.ruslan.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.ruslan.dto.EmailDto;

import java.util.UUID;

@Component
@Aspect
public class EmailAspect {
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    JavaMailSender javaMailSender;

    @AfterReturning(value = "execution(* ru.ruslan.service.SignUpService.signUp(*))", returning = "emailDto")
    public void signUpAdvice(EmailDto emailDto) {
        String uuid = UUID.randomUUID().toString();
        String link = "http://localhost:8080/signUp/confirmation/" + uuid;
        System.err.println("==========ASPeCT POINT==========");;
     /*   var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);*/
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("***");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
    }
}