package ru.linedecor.shop.dto.request.product;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class ProductDto {

    private final Long id;

    @NotEmpty(message = "Name is required")
    private final String name;

    @NotEmpty(message = "Sku is required")
    private final String sku;

    private final ProductDetailsDto details;

    @NotEmpty(message = "Need one or more categories")
    private final Set<Integer> categoryIds;

}
