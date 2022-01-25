package ru.linedecor.shop.dto.request.product.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ru.linedecor.shop.validation.annotations.CharacteristicTypeValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CharacteristicRequestDto {

    private final Long id;

    @NotBlank(message = "Name is required")
    private final String name;

    @CharacteristicTypeValue
    private final String type;

    @NotNull(message = "Characteristic group is required")
    private final Integer groupId;

}
