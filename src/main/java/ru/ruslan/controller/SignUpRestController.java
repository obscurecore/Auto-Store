package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.dto.SignUpDto;
import ru.ruslan.exception.VerificationTokenExpiredException;
import ru.ruslan.service.contract.ConstraintService;
import ru.ruslan.service.contract.SignUpService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.cert.CertificateExpiredException;
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

    @SneakyThrows
    @GetMapping("/confirmation/{link}")
    @ResponseStatus(value = HttpStatus.OK)
    public void confirmRegistration(@PathVariable("link")  String link,HttpServletResponse response) {
        service.confirm(link);
        response.sendRedirect("/signUp");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ExpiredTokenExceptions(VerificationTokenExpiredException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InvalidToken(UsernameNotFoundException ex) {
        return ex.getMessage();
    }
}