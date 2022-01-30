package ru.linedecor.shop.dto.request.product.price.type;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PriceTypeDto {

    private final Integer id;

    @NotBlank(message = "Name is required")
    private final String name;

}
