package ru.ruslan.service;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import ru.ruslan.service.contract.ConstraintService;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConstraintServiceImpl implements ConstraintService {

    public Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> resultB = bindingResult.getAllErrors().stream().collect(
                Collectors.toMap(DefaultMessageSourceResolvable::getCode, DefaultMessageSourceResolvable::getDefaultMessage));
        return resultB;
    }

}