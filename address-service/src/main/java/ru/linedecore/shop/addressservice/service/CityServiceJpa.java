package ru.linedecore.shop.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecore.shop.addressservice.domain.City;
import ru.linedecore.shop.addressservice.domain.Region;
import ru.linedecore.shop.addressservice.dto.request.CityDto;
import ru.linedecore.shop.addressservice.dto.response.CityView;
import ru.linedecore.shop.addressservice.repository.CityRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CityServiceJpa implements CityService {

    private final CityRepository cityRepository;
    private final RegionService regionService;

    @Override
    public List<CityView> getViewsByRegionIdWhereNameStarts(Integer regionId, String nameStarts, Pageable pageable) {
        return cityRepository.getViewsWhereNameStarts(regionId, nameStarts, pageable).getContent();
    }

    @Override
    public CityView getViewById(Long id) {
        return cityRepository.getViewById(id)
                .orElseThrow(() -> new IllegalStateException(""));
    }

    @Transactional
    @Override
    public void createCity(Integer regionId, CityDto dto) {
        Region region = regionService.getById(regionId);
        City newCity = new City(dto);
        region.addCity(newCity);
    }

    @Transactional
    @Override
    public City findById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
