package ru.linedecor.shop.dto.response.product.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface CharacteristicView {

    Integer getId();

    String getName();

    String getType();

    String getGroupName();

}
