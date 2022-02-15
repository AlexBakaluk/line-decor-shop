package ru.linedecore.shop.addressservice.service;

import org.springframework.data.domain.Pageable;
import ru.linedecore.shop.addressservice.domain.City;
import ru.linedecore.shop.addressservice.dto.request.CityDto;
import ru.linedecore.shop.addressservice.dto.response.CityView;

import java.util.List;

public interface CityService {
    List<CityView> getViewsByRegionIdWhereNameStarts(Integer regionId, String nameStarts, Pageable pageable);

    CityView getViewById(Long id);

    void createCity(Integer regionId, CityDto newCity);

    City findById(Long cityId);
}
