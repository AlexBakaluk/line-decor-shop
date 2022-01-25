package ru.linedecor.shop.domain.dto.product.characteristic;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface CharacteristicGroupView {

    Integer getId();

    String getName();

}
