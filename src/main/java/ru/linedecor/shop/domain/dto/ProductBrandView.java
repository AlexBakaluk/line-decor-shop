package ru.linedecor.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface ProductBrandView {

    Integer getId();

    String getName();

}
