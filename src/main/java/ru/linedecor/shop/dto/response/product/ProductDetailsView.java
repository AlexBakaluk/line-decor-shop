package ru.linedecor.shop.dto.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDetailsView implements Serializable {

    private String description;

}
