package ru.ruslan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/login")
    public String getSignInPage() {
        return "login";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

}
