package ru.linedecor.shop.validation.product.brand;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.exception.product.brand.NotValidBrandException;
import ru.linedecor.shop.exception.product.brand.ProductBrandAlreadyExistsException;

@Component
public class BrandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductBrand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductBrand brand = (ProductBrand) target;
        validateBrandName(brand, errors);
        if (errors.hasErrors()) {
            throw new NotValidBrandException(errors);
        }
    }

    private void validateBrandName(ProductBrand brand, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
    }
}
