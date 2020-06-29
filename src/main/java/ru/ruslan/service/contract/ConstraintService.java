package ru.ruslan.service.contract;

import org.springframework.validation.BindingResult;

import java.util.Map;

public interface ConstraintService {
    Map<String, String> getErrors(BindingResult bindingResult);
}
