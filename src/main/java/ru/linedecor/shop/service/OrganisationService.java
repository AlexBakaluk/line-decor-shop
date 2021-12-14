package ru.linedecor.shop.service;

import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.dto.OrganisationDto;

import java.util.List;

public interface OrganisationService {
    List<OrganisationDto> getAllOrganisations();

    OrganisationDto getOrganisationById(long anyLong);

    Organisation createNewOrganisation(Organisation newOrganisation);

    void deleteById(long anyLong);
}
