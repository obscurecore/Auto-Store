package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.service.contract.ConstraintService;
import ru.ruslan.service.contract.SignUpService;
import ru.ruslan.validator.AccountEmail;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/signUp")
@Validated
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

    @SneakyThrows
    @GetMapping("/confirmation/{link}")
    @ResponseStatus(value = HttpStatus.OK)
    public void confirmRegistration(@AccountEmail @PathVariable("link") String link, HttpServletResponse response) {
        response.sendRedirect("/signUp");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintValidationExceptions(ConstraintViolationException ex) {
        return constraintService.getConstraintErrors(ex);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodValidationExceptions(MethodArgumentNotValidException ex) {
        return constraintService.getMethodErrors(ex.getBindingResult());
    }
}