package ru.linedecor.shop.controller.product.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.exception.product.price.PriceTypesTableIsEmptyException;
import ru.linedecor.shop.service.product.price.PriceTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {PriceTypeController.class})
class PriceTypeControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String BASE_URL = "/api/product/price/type";
    private static final List<PriceTypeView> PRICE_TYPE_DTO_LIST = new ArrayList<>();
    private static final PriceType NEW_UNIQUE_TYPE = new PriceType(null, "Unique");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceTypeService service;

    @BeforeEach
    public void setUp() {
        PRICE_TYPE_DTO_LIST.add(
                new PriceTypeView() {
                    @Override
                    public Integer getId() {
                        return 1;
                    }

                    @Override
                    public String getName() {
                        return "Цена продажи";
                    }
                }
        );
    }

    @AfterEach
    public void tearDown() {
        PRICE_TYPE_DTO_LIST.clear();
    }

    @DisplayName("Should return price type dto when get all price types")
    @Test
    @SneakyThrows
    public void givenGetAllTypes_whenTypesTableHasData_thenShouldReturnDtoList() {
        given(service.getAllTypeViews())
                .willReturn(PRICE_TYPE_DTO_LIST);

        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Цена продажи"));
    }

    @DisplayName("Should handle exception when price types table is empty")
    @Test
    @SneakyThrows
    public void givenGetAllTypes_whenTypesTableIsEmpty_thenShouldHandlePriceTypesTableIsEmptyException() {
        given(service.getAllTypeViews())
                .willThrow(new PriceTypesTableIsEmptyException());

        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("message").isNotEmpty());
    }

    @DisplayName("Should create and return new type when create new unique type")
    @Test
    @SneakyThrows
    public void givenSaveNewType_whenNewTypeIsUnique_thenShouldCreateAndReturnEntity() {
        PriceType createdType = new PriceType();
        createdType.setId(3);
        createdType.setName("Created");

//        given(service.createNewPriceType(NEW_UNIQUE_TYPE))
//                .willReturn(createdType);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(NEW_UNIQUE_TYPE)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(createdType.getId()))
                .andExpect(jsonPath("name").value(createdType.getName()));
    }

    @DisplayName("Should handle exception and return status bad request when save not unique type")
    @Test
    @SneakyThrows
    public void givenSaveNewType_whenNewTypeNotUnique_thenShouldHandlePriceTypeAlreadyExistsException() {
        String notUniqueName = PRICE_TYPE_DTO_LIST.get(0).getName();

//        given(service.createNewPriceType(any()))
//                .willThrow(new PriceTypeAlreadyExistsException(notUniqueName));

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(new PriceType(null, notUniqueName))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").isNotEmpty());
    }

}