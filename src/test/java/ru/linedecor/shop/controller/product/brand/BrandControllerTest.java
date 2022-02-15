package ru.linedecor.shop.controller.product.brand;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.exception.product.brand.ProductBrandNotFoundException;
import ru.linedecor.shop.service.product.brand.BrandService;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BrandController.class})
public class BrandControllerTest {

    private static final String BASE_URL = "/api/brand";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final int BRAND_TABLE_SIZE = 5;
    private static final List<ProductBrandView> PRODUCT_BRAND_VIEW_LIST = new ArrayList<>(6);
    private static final int EXISTED_BRAND_ID = 1;
    private static final String EXISTED_BRAND_NAME = "Art Line";
    private static final int NOT_EXISTED_BRAND_ID = -9999;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @MockBean
    private EntityValidator validator;

    @BeforeEach
    public void setUp() {
        BrandTestUtils.fillBrandViewsListWithTestData(PRODUCT_BRAND_VIEW_LIST);
    }

    @AfterEach
    public void tearDown() {
        BrandTestUtils.clearBrandViewsList(PRODUCT_BRAND_VIEW_LIST);
    }

    @DisplayName("Should return list of views with expected size")
    @Test
    @SneakyThrows
    public void givenGetAllBrands_ShouldReturnListOfBrandViewsWithExpectedSize() {
        given(brandService.getAllBrandViewsSortByName())
                .willReturn(PRODUCT_BRAND_VIEW_LIST);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(BRAND_TABLE_SIZE)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString())
                .andExpect(jsonPath("$[5].id").doesNotExist());
    }

    @Test
    @SneakyThrows
    public void givenGetBrandById_ShouldReturnBrandViewById() {
        given(brandService.getViewById(anyInt()))
                .willReturn(PRODUCT_BRAND_VIEW_LIST.get(0));

        val EXPECTED_NAME = PRODUCT_BRAND_VIEW_LIST.get(0).getName();

        mockMvc.perform(get(BASE_URL + "/id/" + EXISTED_BRAND_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(EXPECTED_NAME));
    }

    @Test
    @SneakyThrows
    public void givenGetBrandByNotExistedId_ShouldReturnExceptionMessageWithStatusBadRequest() {
        given(brandService.getViewById(NOT_EXISTED_BRAND_ID))
                .willThrow(new ProductBrandNotFoundException(NOT_EXISTED_BRAND_ID));

        mockMvc.perform(get(BASE_URL + "/id/" + NOT_EXISTED_BRAND_ID))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(NOT_EXISTED_BRAND_ID + " not exists")));
    }

    @Test
    @SneakyThrows
    public void givenCreateNewNotUniqueBrand_ShouldReturnExceptionMessageWithStatusBadRequest() {
        val NOT_UNIQUE_BRAND = new ProductBrand(EXISTED_BRAND_NAME);
//        given(() -> brandService.createNewBrand(NOT_UNIQUE_BRAND))

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(NOT_UNIQUE_BRAND)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(EXISTED_BRAND_NAME + " already exists")));
    }

    @Test
    @SneakyThrows
    public void givenCreateNewNotValidBrand_ShouldReturnExceptionMessageWithStatusBadRequest() {
        val NOT_VALID_BRAND_NAME = "";
        val NOT_VALID_BRAND = new ProductBrand(NOT_VALID_BRAND_NAME);
//        given(brandService.createNewBrand(NOT_VALID_BRAND))
//                .willThrow(new NotValidBrandException("Name of brand must not be null or empty"));

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(NOT_VALID_BRAND)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(EXISTED_BRAND_NAME + " already exists!")));
    }

}
