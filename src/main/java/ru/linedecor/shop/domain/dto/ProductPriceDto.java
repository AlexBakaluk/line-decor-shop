package ru.linedecor.shop.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductPriceDto implements Serializable {

    private final Long priceId;
    private final String priceTypeName;
    private final BigDecimal price;

}
