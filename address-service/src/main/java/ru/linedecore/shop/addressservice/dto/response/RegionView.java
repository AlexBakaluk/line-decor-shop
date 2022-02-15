package ru.linedecore.shop.addressservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface RegionView {

    Integer getId();

    String getName();

}
