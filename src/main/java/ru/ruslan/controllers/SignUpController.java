package ru.ruslan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.service.SignUpService;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDto form) {

        service.signUp(form);
        return "redirect:/signUp";
    }
}