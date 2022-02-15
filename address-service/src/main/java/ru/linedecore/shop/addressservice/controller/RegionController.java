package ru.linedecore.shop.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.linedecore.shop.addressservice.dto.response.RegionView;
import ru.linedecore.shop.addressservice.service.RegionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/address/region")
public class RegionController {

    private final RegionService regionService;

    @GetMapping(path = "/countryId/{countryId}")
    public List<RegionView> getAllByCountryId(@PathVariable Integer countryId) {
        return regionService.getAllViewsByCountryId(countryId);
    }

    @GetMapping(path = "/countryName/{countryName}")
    public List<RegionView> getAllByCountryShortName(@PathVariable String countryName) {
        return regionService.getAllViewsByCountryShortName(countryName);
    }

}
