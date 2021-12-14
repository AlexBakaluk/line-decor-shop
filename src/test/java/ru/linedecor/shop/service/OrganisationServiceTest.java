package ru.linedecor.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import ru.linedecor.shop.NoOrganisationsFoundException;
import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.dto.OrganisationDto;
import ru.linedecor.shop.domain.dto.WarehouseDto;
import ru.linedecor.shop.exception.organisation.OrganisationNotFoundException;
import ru.linedecor.shop.repository.OrganisationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class OrganisationServiceTest {

    private static final List<OrganisationDto> ORGANISATIONS_LIST = new ArrayList<>();

    @MockBean
    private OrganisationRepository organisationRepository;

    @Autowired
    private OrganisationServiceJpa organisationService;

    @BeforeEach
    public void setUp() {
        fillOrganisationList();
    }

    private void fillOrganisationList() {
        OrganisationDto firstOrg = new OrganisationDto() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getName() {
                return "ИП Петров Петр Петрович";
            }

            @Override
            public List<WarehouseDto> getWarehouses() {
                return null;
            }
        };

        OrganisationDto secondOrg = new OrganisationDto() {
            @Override
            public Long getId() {
                return 2L;
            }

            @Override
            public String getName() {
                return "ИП Иванов Иван Иванович";
            }

            @Override
            public List<WarehouseDto> getWarehouses() {
                return null;
            }
        };

        OrganisationDto thirdOrg = new OrganisationDto() {
            @Override
            public Long getId() {
                return 3L;
            }

            @Override
            public String getName() {
                return "ИП Журавлев Игорь Сергеевич";
            }

            @Override
            public List<WarehouseDto> getWarehouses() {
                return null;
            }
        };

        ORGANISATIONS_LIST.addAll(List.of(firstOrg, secondOrg, thirdOrg));
    }

    @Test
    public void whenGetAllOrganisation_thenShouldReturnOrganisationDtoList() {
        given(organisationRepository.getAllOrganisationProjections())
                .willReturn(ORGANISATIONS_LIST);

        var founded = organisationService.getAllOrganisations();
        assertThat(founded)
                .isNotNull()
                .isInstanceOf(List.class)
                .hasSize(ORGANISATIONS_LIST.size())
                .hasSameElementsAs(ORGANISATIONS_LIST);

    }

    @Test
    public void givenGetAllOrganisation_whenOrganisationTableIsEmpty_thenShouldThrowNoOrganisationsFoundException() {
        given(organisationRepository.getAllOrganisationProjections())
                .willReturn(Collections.emptyList());

        assertThatThrownBy(() -> organisationService.getAllOrganisations())
                .isInstanceOf(NoOrganisationsFoundException.class)
                .hasMessage("No data in organisation table!");
    }

    @Test
    public void givenGetOrganisationById_whenOrganisationTableHasDataWithThatId_thenShouldReturnOrganisationFromDb() {
        given(organisationRepository.getOrganisationProjectionById(ORGANISATIONS_LIST.get(0).getId()))
                .willReturn(Optional.of(ORGANISATIONS_LIST.get(0)));

        var founded = organisationService.getOrganisationById(ORGANISATIONS_LIST.get(0).getId());
        assertThat(founded)
                .isNotNull()
                .isInstanceOf(OrganisationDto.class)
                .isEqualTo(ORGANISATIONS_LIST.get(0));
    }

    @Test
    public void givenGetOrganisationById_whenOrganisationTableHasNoDataWithThatId_thenShouldThrowOrganisationNotFoundException() {
        given(organisationRepository.getOrganisationProjectionById(anyLong()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> organisationService.getOrganisationById(1991L))
                .isInstanceOf(OrganisationNotFoundException.class)
                .hasMessage("Organisation with id: 1991 not exists!");
    }

    @Test
    public void givenCreateOrganisation_whenInsertUniqueOrganisation_thenOrganisationShouldBeAddedAndReturnProjection() {
        Organisation newOrganisation = new Organisation();
        newOrganisation.setId(7L);
        newOrganisation.setName("Unique organisation");

        given(organisationRepository.save(any()))
                .willReturn(newOrganisation);

        assertThat(organisationService.createNewOrganisation(newOrganisation))
                .isInstanceOf(OrganisationDto.class)
                .hasFieldOrPropertyWithValue("id", 7L)
                .hasFieldOrPropertyWithValue("name", "Unique organisation");
    }


    @Test
    public void givenDeleteOrganisationById_whenOrganisationTableHasNoDataWithThatId_thenShouldHandleSqlExceptionAndThrowOrganisationNotFoundException() {
        doThrow(new DataIntegrityViolationException("")).when(organisationRepository).deleteById(1997L);

        assertThatThrownBy(() -> organisationService.deleteById(1997L))
                .isInstanceOf(OrganisationNotFoundException.class)
                .hasMessage("Organisation with id: 1991 not exists");
    }
}