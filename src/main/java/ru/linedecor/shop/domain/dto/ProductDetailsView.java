package ru.linedecor.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProductDetailsView implements Serializable {

    private final Long id;
    private final String description;
    private final ProductMeasureDto measure;
    private final List<ProductPriceDto> prices;
    private final Set<ProductCharacteristicDto> characteristics;
}
