package ru.linedecore.shop.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.linedecore.shop.addressservice.dto.response.CountryView;
import ru.linedecore.shop.addressservice.service.CountryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/address/country")
public class CountryController {

    private final CountryService countryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CountryView> getAllCountries() {
        return countryService.getAllViews();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/id/{id}")
    public CountryView getById(@PathVariable Integer id) {
        return countryService.getViewById(id);
    }

}
