package ru.linedecore.shop.addressservice.service;

import ru.linedecore.shop.addressservice.domain.Region;
import ru.linedecore.shop.addressservice.dto.response.RegionView;

import java.util.List;

public interface RegionService {
    List<RegionView> getAllViewsByCountryId(Integer countryId);

    List<RegionView> getAllViewsByCountryShortName(String countryName);

    Region getById(Integer regionId);
}
