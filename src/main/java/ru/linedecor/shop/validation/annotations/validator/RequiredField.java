package ru.linedecor.shop.validation.annotations.validator;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
