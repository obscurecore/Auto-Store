package ru.ruslan.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.service.SignUpService;

@AllArgsConstructor
@Controller
public class SignUpController {
    private SignUpService service;


    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDto form) {
        service.signUp(form);
      /*  if (service.signUp(form)==false) {
            System.err.println("already exist");
        }*/
        return "redirect:/signUp";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = service.activateUser(code);
        if (isActivated) {
            System.err.println("=================ACTIVATED=====================");
        }
        return "login";
    }
}