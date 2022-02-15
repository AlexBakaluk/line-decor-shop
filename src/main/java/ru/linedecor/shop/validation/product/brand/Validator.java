package ru.linedecor.shop.validation.product.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.linedecor.shop.validation.annotations.validator.AnnotationValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class Validator {

    private final Map<String, Set<String>> fieldsValidationErrors = new HashMap<>();

    @Autowired
    private Set<AnnotationValidator> validators;

    public void validate(Object object) {
        for (AnnotationValidator validator : validators) {
            validator.validate(object, fieldsValidationErrors);
        }
    }

}
