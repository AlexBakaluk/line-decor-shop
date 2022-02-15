package ru.linedecor.shop.validation.annotations.validator;

import java.util.Map;
import java.util.Set;

public interface AnnotationValidator {

    void validate(Object object, Map<String, Set<String>> fieldsValidationErrors);
}
