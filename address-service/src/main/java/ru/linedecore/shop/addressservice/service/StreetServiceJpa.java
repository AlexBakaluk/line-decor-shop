package ru.linedecore.shop.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecore.shop.addressservice.domain.City;
import ru.linedecore.shop.addressservice.domain.Street;
import ru.linedecore.shop.addressservice.dto.request.StreetDto;
import ru.linedecore.shop.addressservice.dto.response.StreetView;
import ru.linedecore.shop.addressservice.repository.StreetRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class StreetServiceJpa implements StreetService {

    private final StreetRepository streetRepository;
    private final CityService cityService;

    @Override
    public List<StreetView> getByCityIdWhereNameStarts(Long cityId, String nameStarts, Pageable pageable) {
        return streetRepository.getSliceByCityId(cityId, nameStarts, pageable).getContent();
    }

    @Override
    public StreetView getById(Long id) {
        return streetRepository.getViewById(id)
                .orElseThrow(() -> new IllegalStateException(""));
    }

    @Transactional
    @Override
    public void createStreetAndAddToCity(StreetDto streetDto, Long cityId) {
        City city = cityService.findById(cityId);
        Street newStreet = new Street(streetDto);
        city.addStreet(newStreet);
    }
}
