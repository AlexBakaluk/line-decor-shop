package ru.linedecor.shop.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCharacteristicDto implements Serializable {
    private final Long id;
    private final String name;
    private final String value;
}
