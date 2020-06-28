package ru.ruslan.controller.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
/*
public class ControllerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<Errors, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError. + "Error",
                FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
*/