package ru.linedecor.shop.dto.request.product.characteristic;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CharacteristicGroupRequestDto {

    final Integer id;

    @NotBlank(message = "Name is required")
    final String name;
}
