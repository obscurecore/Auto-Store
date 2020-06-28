package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ru.ruslan.dto.SignUpDto;
import ru.ruslan.model.User;
import ru.ruslan.service.interf.SignUpService;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Controller
public class SignUpController {
    private SignUpService service;
    private Validator validator;
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(
            @Valid SignUpDto signUpDto,
            BindingResult bindingResult,
            Model model
    ) {


        System.err.println("+++++"+bindingResult.getFieldErrors().toString());
        System.err.println("GLOVBAL ERROR+ "+bindingResult.getGlobalErrors().toString());
        Set<ConstraintViolation<SignUpDto>> failures = validator
                .validate(signUpDto);
        /*
        System.err.println(Arrays.toString(failures.toArray()));*/
      /*  if (bindingResult.hasErrors()) {

         //   Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            System.err.println("=========LOG+++++++");
            System.err.println(new PrettyPrintingMap<String, String>(errorsMap));
        }*/

      /*  } else {
            service.signUp(signUpDto);
        }*/

      /*  if (service.signUp(form)==false) {
            System.err.println("already exist");
        }*/

        return "redirect:/signUp";
    }


/*
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = service.activateUser(code);
        if (isActivated) {
            System.err.println("=================ACTIVATED=====================");
        }
        return "login";
    }*/
   /* @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Bucket with the same title already exists"));
    }*/
}