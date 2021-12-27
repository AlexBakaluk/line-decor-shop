package ru.linedecor.shop.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductMeasureDto implements Serializable {
    private final Integer id;
    private final String name;
}
