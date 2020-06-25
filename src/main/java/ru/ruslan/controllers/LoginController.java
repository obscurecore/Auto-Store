package ru.ruslan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ruslan.dto.SignUpDto;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }


    @PostMapping("/login")
    public String signUp(SignUpDto form) {
        System.err.println(form.getPassword());
        // service.signUp(form);
        return "redirect:/login";
    }
}