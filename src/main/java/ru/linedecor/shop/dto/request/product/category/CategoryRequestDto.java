package ru.linedecor.shop.dto.request.product.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRequestDto {

    final Integer id;

    @NotBlank(message = "Name is required")
    final String name;

}
