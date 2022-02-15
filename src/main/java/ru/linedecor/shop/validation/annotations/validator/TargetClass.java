package ru.linedecor.shop.validation.annotations.validator;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetClass {

    Class<?> targetClass();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
