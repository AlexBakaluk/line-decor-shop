package ru.linedecor.shop.dto.request.product;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ProductDetailsDto {

    @NotNull(message = "Brand is required")
    private final Integer brandId;

    private final String description;

    @NotNull(message = "Measure type is required")
    private final Integer measureId;

    private final Set<Long> priceIds;

    private final Set<Long> characteristicIds;

}
