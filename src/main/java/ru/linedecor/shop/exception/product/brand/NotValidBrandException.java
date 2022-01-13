package ru.linedecor.shop.exception.product.brand;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.StringJoiner;

public class NotValidBrandException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Brand validation errors: [";
    private static final String MESSAGE_SUFFIX = "]";

    public NotValidBrandException(Errors errors) {
        String exceptionMessage = generateExceptionMessage(errors);
    }

    private String generateExceptionMessage(Errors errors) {
        StringJoiner joiner = new StringJoiner(MESSAGE_PREFIX);
        for (ObjectError error : errors.getAllErrors()) {
        }
    }
}
