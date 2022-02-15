package ru.linedecor.shop.validation.product.brand;

import ru.linedecor.shop.validation.annotations.validator.AnnotationValidator;

import java.util.Map;
import java.util.Set;

public abstract class AbstractAnnotationValidator implements AnnotationValidator {

    public final void validate(Object object, Map<String, Set<String>> fieldsValidationErrors) {
        boolean contains = checkIsObjectContainsServicedAnnotation(object);
        if (contains) {
            validateObject(object, fieldsValidationErrors);
        }
    }

    protected abstract void validateObject(Object object, Map<String, Set<String>> fieldsValidationErrors);

    protected abstract boolean checkIsObjectContainsServicedAnnotation(Object object);
}
