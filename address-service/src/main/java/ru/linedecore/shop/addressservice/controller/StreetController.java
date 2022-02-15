package ru.linedecore.shop.addressservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.linedecore.shop.addressservice.dto.request.StreetDto;
import ru.linedecore.shop.addressservice.dto.response.StreetView;
import ru.linedecore.shop.addressservice.service.StreetService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/address/street")
public class StreetController {

    private final StreetService streetService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/cityId/{cityId}/name/{nameStarts}")
    public List<StreetView> getByCityIdWhereNameStarts(
            @PageableDefault(size = 100, sort = {"name"}) Pageable pageable,
            @PathVariable Long cityId,
            @PathVariable String nameStarts) {
        return streetService.getByCityIdWhereNameStarts(cityId, nameStarts, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/id/{id}")
    public StreetView getById(@PathVariable Long id) {
        return streetService.getById(id);
    }

    public void createNewStreetAndAddToCityById(
            @PathVariable Long cityId,
            @RequestBody StreetDto streetDto
    ) {
        streetService.createStreetAndAddToCity(streetDto, cityId);
    }

}
