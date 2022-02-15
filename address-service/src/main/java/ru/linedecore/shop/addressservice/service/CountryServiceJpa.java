package ru.linedecore.shop.addressservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecore.shop.addressservice.dto.response.CountryView;
import ru.linedecore.shop.addressservice.repository.CountryRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CountryServiceJpa implements CountryService {

    private final CountryRepository countryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CountryView> getAllViews() {
        return countryRepository.getAllViews();
    }

    @Transactional(readOnly = true)
    @Override
    public CountryView getViewById(Integer id) {
        return countryRepository.getViewById(id)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
