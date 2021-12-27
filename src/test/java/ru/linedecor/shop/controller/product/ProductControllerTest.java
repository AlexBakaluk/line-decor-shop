package ru.linedecor.shop.controller.product;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.linedecor.shop.service.product.ProductService;
import ru.linedecor.shop.utils.ProductTestUtils;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {ProductController.class})
public class ProductControllerTest {

    private static final String BASE_URL = "/api/product";
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final String DEFAULT_SORT_PARAM = "name";
    private static int TOTAL_ELEMENTS_IN_PRODUCT_TABLE;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        TOTAL_ELEMENTS_IN_PRODUCT_TABLE = ProductTestUtils.getTotalProductTableSize();
    }


    @DisplayName("Should return first page of ProductView with default size, sort by name when perform getProductPage without params")
    @Test
    @SneakyThrows
    public void givenGetProductFirstPageWithoutParams_thenShouldReturnProductFirstPageWithDefaultSize() {
        given(productService.getProductPage(any(Pageable.class)))
                .willReturn(ProductTestUtils.getTestPage(0, DEFAULT_PAGE_SIZE, Sort.by(DEFAULT_SORT_PARAM)));

        val EXPECTED_CONTENT_SIZE = TOTAL_ELEMENTS_IN_PRODUCT_TABLE / DEFAULT_PAGE_SIZE;
        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(DEFAULT_PAGE_SIZE)))
                .andExpect(jsonPath("$.totalPages").value(EXPECTED_CONTENT_SIZE))
                .andExpect(jsonPath("$.size").value(DEFAULT_PAGE_SIZE))
                .andExpect(jsonPath("$.numberOfElements").value(DEFAULT_PAGE_SIZE))
                .andExpect(jsonPath("$.totalElements").value(TOTAL_ELEMENTS_IN_PRODUCT_TABLE))
                .andExpect(jsonPath("$.first").value(true));
    }

    @DisplayName("Should return not full last page of ProductView with expected size and default sort")
    @Test
    @SneakyThrows
    public void givenGetProductPageWithSize30AndPage3_thenShouldReturnNotFullLastPageDefaultSortByName() {
        val REQUEST_PAGE_NUMBER = 3;
        val REQUEST_PAGE_SIZE = 30;
        given(productService.getProductPage(any(Pageable.class)))
                .willReturn(ProductTestUtils.getTestPage(REQUEST_PAGE_NUMBER, REQUEST_PAGE_SIZE, Sort.by(DEFAULT_SORT_PARAM)));

        val EXPECTED_CONTENT_SIZE = TOTAL_ELEMENTS_IN_PRODUCT_TABLE - (REQUEST_PAGE_NUMBER * REQUEST_PAGE_SIZE);
        val EXPECTED_TOTAL_PAGES = Math.ceil(TOTAL_ELEMENTS_IN_PRODUCT_TABLE / (double) REQUEST_PAGE_SIZE);
        mvc.perform(get(BASE_URL)
                        .param("pageNumber", String.valueOf(REQUEST_PAGE_NUMBER))
                        .param("pageSize", String.valueOf(REQUEST_PAGE_SIZE)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(EXPECTED_CONTENT_SIZE)))
                .andExpect(jsonPath("$.first").value(false))
                .andExpect(jsonPath("$.numberOfElements").value(EXPECTED_CONTENT_SIZE))
                .andExpect(jsonPath("$.last").value(true))
                .andExpect(jsonPath("$.sort.sorted").value(true))
                .andExpect(jsonPath("$.totalPages").value(EXPECTED_TOTAL_PAGES))
                .andExpect(jsonPath("$.number").value(REQUEST_PAGE_NUMBER));
    }

    @Test
    @SneakyThrows
    public void givenGetAllProductByNameOrSku_when11ProductsContainsThatParams_thenShouldReturnProductPageWhereProductNameOrSkuLikeSearchString() {
        val REQUEST_SEARCH_PARAM = "Test product 1";
        given(productService.getProductPageByNameOrSkuLike(eq(REQUEST_SEARCH_PARAM), any(Pageable.class)))
                .willReturn(ProductTestUtils.getAllWhereNameLike(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, REQUEST_SEARCH_PARAM));

        val EXPECTED_TOTAL_ELEMENTS = 11;
        val EXPECTED_TOTAL_PAGES = 2;

        mvc.perform(get(BASE_URL + "/nameOrSku/" + REQUEST_SEARCH_PARAM))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(DEFAULT_PAGE_SIZE)))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.numberOfElements").value(DEFAULT_PAGE_SIZE))
                .andExpect(jsonPath("$.last").value(false))
                .andExpect(jsonPath("$.totalPages").value(EXPECTED_TOTAL_PAGES))
                .andExpect(jsonPath("$.totalElements").value(EXPECTED_TOTAL_ELEMENTS))
                .andExpect(jsonPath("$.sort.sorted").value(true));
    }

    @Test
    @SneakyThrows
    public void givenGetAllProductByNameOrSku_when19ProductsContainsThatParams_thenShouldReturnProductPageWhereProductNameOrSkuLikeSearchString() {
        val REQUEST_SEARCH_PARAM = "1";
        val PAGE_SIZE = 5;
        given(productService.getProductPageByNameOrSkuLike(eq(REQUEST_SEARCH_PARAM), any(Pageable.class)))
                .willReturn(ProductTestUtils.getAllWhereNameLike(DEFAULT_PAGE_NUMBER, PAGE_SIZE, REQUEST_SEARCH_PARAM));

        val EXPECTED_TOTAL_ELEMENTS = 19;
        val EXPECTED_TOTAL_PAGES = 4;

        mvc.perform(get(BASE_URL + "/nameOrSku/" + REQUEST_SEARCH_PARAM))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(PAGE_SIZE)))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.numberOfElements").value(PAGE_SIZE))
                .andExpect(jsonPath("$.last").value(false))
                .andExpect(jsonPath("$.totalPages").value(EXPECTED_TOTAL_PAGES))
                .andExpect(jsonPath("$.totalElements").value(EXPECTED_TOTAL_ELEMENTS))
                .andExpect(jsonPath("$.sort.sorted").value(true));
    }

}
