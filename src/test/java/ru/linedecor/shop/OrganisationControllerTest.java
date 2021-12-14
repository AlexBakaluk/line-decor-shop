package ru.linedecor.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.linedecor.shop.controller.OrganisationController;
import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.Warehouse;
import ru.linedecor.shop.domain.dto.OrganisationDto;
import ru.linedecor.shop.domain.dto.WarehouseDto;
import ru.linedecor.shop.exception.organisation.OrganisationAlreadyExistsException;
import ru.linedecor.shop.exception.organisation.OrganisationNotFoundException;
import ru.linedecor.shop.service.OrganisationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OrganisationController.class})
public class OrganisationControllerTest {

    private static final List<OrganisationDto> ORGANISATIONS_LIST = new ArrayList<>();
    private static final long EXISTED_ORGANISATION_ID = 2L;
    private static final long NOT_EXISTED_ORGANISATION_ID = 1991L;
    private static final WarehouseDto DEFAULT_WAREHOUSE =
            new WarehouseDto(1L, "Default", "Address", "Number");
    private static Organisation CREATED_ORGANISATION;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganisationService organisationService;

    @BeforeEach
    public void setUp() {
        fillOrganisationListWithTestData();
    }

    private void fillOrganisationListWithTestData() {
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

        CREATED_ORGANISATION = new Organisation(5L, "Created", List.of(new Warehouse(1L, "Default", null, null, CREATED_ORGANISATION)));
    }

    @DisplayName("Should return full organisation list")
    @Test
    @SneakyThrows
    public void whenGetAllOrganisation_ThenShouldReturnOrganisationList() {
        given(organisationService.getAllOrganisations())
                .willReturn(ORGANISATIONS_LIST);

        mockMvc.perform(get("/api/organisations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ИП Петров Петр Петрович"))
                .andExpect(jsonPath("$[2].name").value("ИП Журавлев Игорь Сергеевич"))
                .andExpect(jsonPath("$[0].warehouses").doesNotExist());
    }

    @Test
    @SneakyThrows
    public void wheGetAllOrganisationThrowsNoOrganisationsFoundException_thenShouldHandleAndReturnStatusNoContentWithMessage() {
        given(organisationService.getAllOrganisations())
                .willThrow(new NoOrganisationsFoundException());

        mockMvc.perform(get("/api/organisations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("message").value("No data in organisation table!"));
    }

    @DisplayName("Should return organisationDto when try to find organisation by existed id")
    @Test
    @SneakyThrows
    public void whenGetByExistedId_ThenShouldReturnOrganisation() {
        given(organisationService.getOrganisationById(anyLong()))
                .willReturn(ORGANISATIONS_LIST.get(2));

        mockMvc.perform(
                        get("/api/organisations/id/" + EXISTED_ORGANISATION_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("ИП Журавлев Игорь Сергеевич"))
                .andExpect(jsonPath("id").value(3));
    }

    @DisplayName("Should return exception message with status code Not Found")
    @Test
    @SneakyThrows
    public void whenGetByNotExistedId_ThenShouldHandleOrganisationNotFoundExceptionWithMessage() {
        given(organisationService.getOrganisationById(anyLong()))
                .willThrow(new OrganisationNotFoundException(2L));

        mockMvc.perform(
                        get("/api/organisations/id/" + NOT_EXISTED_ORGANISATION_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("Organisation with id: 2 not exists!"));
    }

    @DisplayName("Should create new organisation and return dto when post create new organisation")
    @Test
    @SneakyThrows
    public void whenPostNewOrganisation_ThenShouldReturnStatusCreated() {
        given(organisationService.createNewOrganisation(any()))
                .willReturn(CREATED_ORGANISATION);
        Organisation newOrganisation = new Organisation(null, "New Created organisation", null);

        mockMvc.perform(post("/api/organisations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newOrganisation)))
                .andExpect(status().isCreated());
    }

    @DisplayName("Should handle OrganisationAlreadyExistsException with message? when not unique organisation inserted")
    @Test
    @SneakyThrows
    public void whenPostNotUniqueOrganisation_ThenShouldHandleOrganisationAlreadyExistsExceptionWithMessage() {
        given(organisationService.createNewOrganisation(any()))
                .willThrow(new OrganisationAlreadyExistsException());

        Organisation newOrganisation = new Organisation(null, "ИП Петров Петр Петрович", null);

        mockMvc.perform(post("/api/organisations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newOrganisation)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("Organisation already exists!"));
    }

    @DisplayName("Should return dto with default warehouse when create new organisation")
    @Test
    @SneakyThrows
    public void whenPostNewOrganisation_ThenShouldReturnOrganisationWithWarehouse() {
        given(organisationService.createNewOrganisation(any()))
                .willReturn(CREATED_ORGANISATION);

        Organisation newOrg = new Organisation(null, "ИП Петров Петр Петрович", null);

        mockMvc.perform(post("/api/organisations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(newOrg)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("warehouses").isNotEmpty())
                .andExpect(jsonPath("warehouses[0].name").value("Default"));
    }

    @DisplayName("Should return status OK when delete existed organisation by id")
    @Test
    @SneakyThrows
    public void whenDeleteExistedOrganisationById_ThenShouldReturnStatusOk() {
        mockMvc.perform(delete("/api/organisations/id/" + EXISTED_ORGANISATION_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(EXISTED_ORGANISATION_ID)))
                .andExpect(status().isOk());
    }

    @DisplayName("Should handle exception and return message when delete not existed organisation")
    @Test
    @SneakyThrows
    public void whenDeleteNotExistedOrganisationById_ThenShouldHandleOrganisationNotFoundExceptionWithMessage() {
        doThrow(new OrganisationNotFoundException(NOT_EXISTED_ORGANISATION_ID))
                .when(organisationService).deleteById(anyLong());

        mockMvc.perform(delete("/api/organisations/id/" + NOT_EXISTED_ORGANISATION_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value(
                        "Organisation with id: " + NOT_EXISTED_ORGANISATION_ID + " not exists!"));
    }

}
