package ru.ruslan.service.contract;

import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolationException;
import java.util.Map;

public interface ConstraintService {
    Map<String, String> getMethodErrors(BindingResult bindingResult);
    Map<String, String> getConstraintErrors(ConstraintViolationException constraintViolationException);

}
