package ru.linedecor.shop.dto.response.product.measure;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface MeasureView {

    Integer getId();

    String getName();

}
