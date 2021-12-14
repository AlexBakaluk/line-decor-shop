package ru.linedecor.shop.integration.product.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.repository.product.price.PriceTypeRepository;
import ru.linedecor.shop.service.product.price.PriceTypeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.vladmihalcea.sql.SQLStatementCountValidator.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(scripts = {"classpath:sql/product/price/fill_price_types.sql"})
@Sql(scripts = {"classpath:sql/product/price/clear_price_types.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
@WebAppConfiguration
public class ProductTypeIntegrationTest {

    private static final int EXPECTED_TABLE_SIZE = 5;
    private static final String BASE_URL = "/api/product/price/type";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String NOT_UNIQUE_TYPE_NAME = "Цена продажи";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @SpyBean
    private PriceTypeService service;

    @SpyBean
    private PriceTypeRepository repository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        SQLStatementCountValidator.reset();
    }

    @Test
    @SneakyThrows
    public void givenGetAllTypes_thenShouldReturnProjectionListWithExpectedSize() {
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Цена продажи"))
                .andExpect(jsonPath("$[2].name").value("Оптовая цена"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        assertSelectCount(1);
        assertInsertCount(0);
        assertDeleteCount(0);
        assertUpdateCount(0);
        verify(service, times(1)).getAllTypes();
        verifyNoMoreInteractions(service);
        verify(repository, times(1)).getAllTypesProjections();
        verifyNoMoreInteractions(repository);
    }

    @Sql(scripts = {"classpath:sql/product/price/clear_price_types.sql"})
    @Test
    @SneakyThrows
    public void givenGetAllTypes_whenTableIsEmpty_thenShouldReturnStatusNoContentWithMessage() {
        mockMvc.perform(get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("message").isNotEmpty())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        assertSelectCount(1);
        assertInsertCount(0);
        assertDeleteCount(0);
        assertUpdateCount(0);
    }

    @Test
    @SneakyThrows
    public void givenCreateNewType_whenNewTypeIsUnique_thenShouldReturnCreatedEntityWithStatusCreated() {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(new PriceType(null, "Unique value"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("name").isNotEmpty());
        assertSelectCount(0);
        assertInsertCount(1);
        assertDeleteCount(0);
        assertUpdateCount(0);
    }

    @Test
    @SneakyThrows
    public void givenCreateNewType_whenNewTypeNotUnique_thenShouldReturnExceptionMessageWithStatusBadRequest() {

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(new PriceType(null, NOT_UNIQUE_TYPE_NAME))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").isNotEmpty());
        assertSelectCount(0);
        assertInsertCount(1);
        assertDeleteCount(0);
        assertUpdateCount(0);
    }
}
