package ru.linedecor.shop.validation.annotations;

import ru.linedecor.shop.validation.annotations.validator.CharacteristicTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CharacteristicTypeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CharacteristicTypeValue {
    String message() default "Invalid characteristic type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
