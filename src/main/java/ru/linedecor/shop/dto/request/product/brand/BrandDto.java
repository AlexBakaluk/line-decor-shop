package ru.linedecor.shop.dto.request.product.brand;

import lombok.Data;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.validation.annotations.UniqueConstraints;
import ru.linedecor.shop.validation.annotations.validator.RequiredField;
import ru.linedecor.shop.validation.annotations.validator.TargetClass;

@UniqueConstraints(target = @TargetClass(targetClass = ProductBrand.class))
@Data
public class BrandDto {

    private Integer id;

    @RequiredField
    private String name;

}
