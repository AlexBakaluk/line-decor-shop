package ru.linedecore.shop.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecore.shop.addressservice.dto.request.CityDto;
import ru.linedecore.shop.addressservice.dto.response.CityView;
import ru.linedecore.shop.addressservice.service.CityService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/address/city")
public class CityController {

    private final CityService cityService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/regionId/{regionId}/nameStarts/{nameStarts}")
    public List<CityView> getAllByRegionIdWhereNameStarts(
            @PageableDefault(size = 100, sort = {"name"}) Pageable pageable,
            @PathVariable Integer regionId, @PathVariable String nameStarts
    ) {
        return cityService.getViewsByRegionIdWhereNameStarts(regionId, nameStarts, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/id/{id}")
    public CityView getById(@PathVariable Long id) {
        return cityService.getViewById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/regionId/{regionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewCity(
            @PathVariable Integer regionId,
            @RequestBody CityDto newCity
    ) {
        cityService.createCity(regionId, newCity);
    }

}
