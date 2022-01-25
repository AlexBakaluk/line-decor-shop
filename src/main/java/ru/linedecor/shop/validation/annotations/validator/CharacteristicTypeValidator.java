package ru.linedecor.shop.validation.annotations.validator;

import lombok.val;
import ru.linedecor.shop.domain.product.CharacteristicType;
import ru.linedecor.shop.validation.annotations.CharacteristicTypeValue;
import ru.linedecor.shop.validation.annotations.CharacteristicTypeValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharacteristicTypeValidator implements ConstraintValidator<CharacteristicTypeValue, String> {

    @Override
    public void initialize(CharacteristicTypeValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        for (CharacteristicType t: CharacteristicType.values()) {
            if (t.getName().equals(type)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
