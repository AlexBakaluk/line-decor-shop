package ru.linedecor.shop.service.product.price;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.exception.product.price.PriceTypeAlreadyExistsException;
import ru.linedecor.shop.exception.product.price.PriceTypesTableIsEmptyException;
import ru.linedecor.shop.repository.product.price.PriceTypeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class PriceTypeServiceJpaTest {

    private static final List<PriceTypeView> TYPES_DTO_LIST = new ArrayList<>();
    private static final int EXPECTED_TYPES_TABLE_SIZE = 2;
    private static final String UNIQUE_PRICE_TYPE_NAME = "Unique value";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PriceTypeService service;

    @MockBean
    private PriceTypeRepository repository;

    @BeforeEach
    public void setUp() {
        fillTypesListWithTestData();
    }

    @AfterEach
    public void tearDown() {
        TYPES_DTO_LIST.clear();
    }

    private void fillTypesListWithTestData() {
        PriceTypeView sellType = new PriceTypeView() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getName() {
                return "Цена продажи";
            }
        };

        PriceTypeView wholesaleType = new PriceTypeView() {
            @Override
            public Integer getId() {
                return 2;
            }

            @Override
            public String getName() {
                return "Оптовая цена";
            }
        };
        TYPES_DTO_LIST.addAll(List.of(sellType, wholesaleType));
    }

    @DisplayName("Should return List of PriceTypeDto with expected size")
    @Test
    public void givenGetAllPriceTypes_whenTypesTableHasData_thenReturnTypesList() {
        given(repository.getAllTypesProjections())
                .willReturn(TYPES_DTO_LIST);

        assertThat(service.getAllTypes())
                .isInstanceOf(List.class)
                .hasSize(EXPECTED_TYPES_TABLE_SIZE)
                .hasSameElementsAs(TYPES_DTO_LIST);
    }

    @DisplayName("Should throw PriceTypesTableIsEmptyException when PriceTypes table is empty")
    @Test
    public void givenGetAllPriceTypes_whenTypesTableIsEmpty_thenShouldThrowPriceTypesTableIsEmptyException() {
        given(repository.getAllTypesProjections())
                .willReturn(List.of());

        assertThatThrownBy(() -> service.getAllTypes())
                .isInstanceOf(PriceTypesTableIsEmptyException.class)
                .hasMessageEndingWith("has no data");
    }

    @DisplayName("Should add new price type when insert unique value")
    @Test
    public void givenSaveUniquePriceType_thenShouldAddNewTypeToTable() {
        PriceType newValue = new PriceType();
        newValue.setId(3);
        newValue.setName(UNIQUE_PRICE_TYPE_NAME);

        given(repository.save(any()))
                .willReturn(newValue);

        assertThat(service.createNewPriceType(new PriceType(null, UNIQUE_PRICE_TYPE_NAME)))
                .isNotNull()
                .isInstanceOf(PriceType.class)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("name", UNIQUE_PRICE_TYPE_NAME);
    }

    @Test
    @DisplayName("Should throw PriceTypeAlreadyExistsException when save not unique type")
    public void givenSaveNotUniquePriceType_thenShouldThrowPriceTypeAlreadyExistsException() {
        given(repository.save(any()))
                .willThrow(new DataIntegrityViolationException("Any message"));
        String alreadyExistsTypeName = TYPES_DTO_LIST.get(0).getName();

        assertThatThrownBy(() -> service.createNewPriceType(new PriceType(null, alreadyExistsTypeName)))
                .isInstanceOf(PriceTypeAlreadyExistsException.class)
                .hasMessageContaining(alreadyExistsTypeName + " already exists");
    }

}