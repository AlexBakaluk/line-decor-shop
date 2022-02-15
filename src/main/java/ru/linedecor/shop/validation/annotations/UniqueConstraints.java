package ru.linedecor.shop.validation.annotations;

import ru.linedecor.shop.validation.annotations.validator.TargetClass;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueConstraints {

    TargetClass target();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
