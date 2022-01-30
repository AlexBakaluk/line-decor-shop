package ru.linedecor.shop.dto.response.product.category;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface CategoryView {

    Integer getId();

    String getName();

}
