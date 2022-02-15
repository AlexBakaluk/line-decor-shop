package ru.linedecor.shop.dto.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface ProductView {

    Long getId();

    String getName();

    String getSku();

    String getDescription();

    String getBrandName();

    String getMeasureName();

}
