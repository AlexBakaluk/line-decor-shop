package ru.linedecore.shop.addressservice.service;

import ru.linedecore.shop.addressservice.dto.response.CountryView;

import java.util.List;

public interface CountryService {
    List<CountryView> getAllViews();

    CountryView getViewById(Integer id);
}
