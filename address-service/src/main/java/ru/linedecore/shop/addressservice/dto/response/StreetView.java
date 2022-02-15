package ru.linedecore.shop.addressservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface StreetView {

    Long getId();

    String getName();

}
