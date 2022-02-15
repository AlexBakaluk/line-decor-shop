package ru.linedecore.shop.addressservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface CountryView {

    Integer getId();

    String getCode();

    String getShortName();

    String getFullName();

    String getAlpha2();

    String getAlpha3();

}
