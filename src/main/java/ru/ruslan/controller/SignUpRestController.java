package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.service.contract.ConstraintService;
import ru.ruslan.service.contract.SignUpService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/signUp")
public class SignUpRestController {
    private ConstraintService constraintService;
    private SignUpService service;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void signUp(@Valid @RequestBody SignUpDto signUpDto, HttpServletResponse response) {
        service.signUp(signUpDto);
        response.sendRedirect("signUp");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return constraintService.getErrors(ex.getBindingResult());
    }
/*
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }*/
/*
    @SneakyThrows
    @PostMapping("/signUp")
    public Map<String, String> signUp(
            @Valid SignUpDto signUpDto,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = constraintService.getErrors(bindingResult);
            return errorsMap;
        }


      /*  } else {
            service.signUp(signUpDto);
        }*/

      /*  if (service.signUp(form)==false) {
            System.err.println("already exist");
        }*/

    //return  httpServletResponse.sendRedirect("signUp");
   /*    return null;
    }
*/


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