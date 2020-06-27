package ru.ruslan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ruslan.repository.UserRepository;

@SpringBootApplication
public class Application{

    @Autowired
    UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*
    @Override
    public void run(String... args) throws Exception {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setExpiryDate();
        VerificationToken verificationToken1 = VerificationToken.builder()
                .token("TOKEN")
                .expiryDate()
                .build();

        User user = User.builder()
                .email("QWE")
                .password("QWERTY007")
                .username("RUSLAN")
                .state(State.NOT_CONFIRMED)
                .roles(Collections.singleton(Role.USER))
                .activationCode(UUID.randomUUID().toString())
                .verificationToken(verificationToken)
                .build();
        userRepository.save(user);
        //User user1 = userRepository.findUserByVerificationToken_Token("TOKEN");
       // System.out.println(user1.getUsername());
    }*/
}
