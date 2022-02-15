package ru.linedecor.shop.controller.product;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.linedecor.shop.service.product.ProductDetailsService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ProductDetailsController.class})
class ProductDetailsControllerTest {

    private static final String BASE_URL = "/api/product/details";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductDetailsService detailsService;

    @Test
    @SneakyThrows
    public void givenGetProductDetailsWithCharacteristicsAndMeasure_thenShouldReturnProductDetails() {
        val REQUEST_PRODUCT_ID = 1;
//        given(detailsService.getProductDetailsWithCharacteristicsAndMeasureByProductId(anyLong()))
//                .willReturn(ProductTestUtils.createTestProductDetailsWithCharacteristicsAndMeasure());

        mvc.perform(get(BASE_URL + "/characteristicsAndMeasure/" + REQUEST_PRODUCT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(REQUEST_PRODUCT_ID))
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.measure").hasJsonPath())
                .andExpect(jsonPath("$.measure.name").isNotEmpty())
                .andExpect(jsonPath("$.prices").doesNotExist())
                .andExpect(jsonPath("$.characteristics", hasSize(5)));
    }

    @Test
    @SneakyThrows
    public void givenGetProductFullDetails_thenShouldReturnProductFullDetailsDto() {
//        val REQUEST_PRODUCT_ID = 1;
//        given(detailsService.getFullProductDetails(anyLong()))
//                .willReturn(ProductTestUtils.createTestProductFullDetails());

//        mvc.perform(get(BASE_URL + "/full/" + REQUEST_PRODUCT_ID))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.id").value(REQUEST_PRODUCT_ID))
//                .andExpect(jsonPath("$.description").isNotEmpty())
//                .andExpect(jsonPath("$.measure").hasJsonPath())
//                .andExpect(jsonPath("$.measure.name").isNotEmpty())
//                .andExpect(jsonPath("$.prices").hasJsonPath())
//                .andExpect(jsonPath("$.prices", hasSize(5)))
//                .andExpect(jsonPath("$.characteristics", hasSize(5)));
    }

}