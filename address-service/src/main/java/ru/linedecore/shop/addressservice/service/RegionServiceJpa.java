package ru.linedecore.shop.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecore.shop.addressservice.domain.Region;
import ru.linedecore.shop.addressservice.dto.response.RegionView;
import ru.linedecore.shop.addressservice.repository.RegionRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RegionServiceJpa implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<RegionView> getAllViewsByCountryId(Integer countryId) {
        return regionRepository.getAllViewsByCountryId(countryId);
    }

    @Override
    public List<RegionView> getAllViewsByCountryShortName(String countryName) {
        return regionRepository.getAllViewsByCountryShortName(countryName);
    }

    @Transactional
    @Override
    public Region getById(Integer regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
