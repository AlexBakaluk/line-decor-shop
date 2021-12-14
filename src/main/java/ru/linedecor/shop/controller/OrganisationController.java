package ru.linedecor.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.dto.OrganisationDto;
import ru.linedecor.shop.service.OrganisationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/organisations", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganisationController {

    private final OrganisationService organisationService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<OrganisationDto> getAll() {
        return organisationService.getAllOrganisations();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/id/{id}")
    public OrganisationDto getById(@PathVariable long id) {
        return organisationService.getOrganisationById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public Organisation createNew(@RequestBody Organisation newOrganisation) {
        return organisationService.createNewOrganisation(newOrganisation);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable long id) {
        organisationService.deleteById(id);
    }
}
