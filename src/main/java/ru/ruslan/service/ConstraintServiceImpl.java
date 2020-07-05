package ru.ruslan.service;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.ruslan.service.contract.ConstraintService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for errors processing
 */
@Component
public class ConstraintServiceImpl implements ConstraintService {

    public Map<String, String> getMethodErrors( MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream().collect(
                Collectors.toMap(DefaultMessageSourceResolvable::getCode, DefaultMessageSourceResolvable::getDefaultMessage));
    }

    @Override
    public Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations()
                .stream().collect(
                        Collectors.toMap(ConstraintViolation::getMessageTemplate, ConstraintViolation::getMessage)
                );
    }

}