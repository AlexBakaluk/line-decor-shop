package ru.linedecor.shop.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.linedecor.shop.exception.EntityValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Scope("prototype")
public class EntityValidator<T> {

    private final static String MESSAGE_PREFIX = "Validation errors:";
    private final Validator validator;

    public void validate(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder(MESSAGE_PREFIX);
            for (ConstraintViolation<T> violation : violations) {
                builder.append("\n");
                builder.append(violation.getMessage());
            }
            throw new EntityValidationException(builder.toString());
        }
    }
}
