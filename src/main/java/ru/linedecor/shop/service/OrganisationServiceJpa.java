package ru.linedecor.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.linedecor.shop.NoOrganisationsFoundException;
import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.dto.OrganisationDto;
import ru.linedecor.shop.exception.organisation.OrganisationAlreadyExistsException;
import ru.linedecor.shop.exception.organisation.OrganisationNotFoundException;
import ru.linedecor.shop.repository.OrganisationRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class OrganisationServiceJpa implements OrganisationService {

    private OrganisationRepository organisationRepository;

    @Override
    public List<OrganisationDto> getAllOrganisations() {
        var allOrganisations = organisationRepository.getAllOrganisationProjections();
        if (allOrganisations.size() == 0) {
            throw new NoOrganisationsFoundException();
        } else {
            return allOrganisations;
        }
    }

    @Override
    public OrganisationDto getOrganisationById(long id) {
        return organisationRepository.getOrganisationProjectionById(id)
                .orElseThrow(() -> new OrganisationNotFoundException(id));
    }

    @Override
    public Organisation createNewOrganisation(Organisation newOrganisation) {
        try {
            return organisationRepository.save(newOrganisation);
        } catch (DataIntegrityViolationException e) {
            throw new OrganisationAlreadyExistsException();
        }
    }

    @Override
    public void deleteById(long id) {
        try {
            organisationRepository.deleteById(id);
        } catch (Exception e) {
            throw new OrganisationNotFoundException(id);
        }
    }
}
