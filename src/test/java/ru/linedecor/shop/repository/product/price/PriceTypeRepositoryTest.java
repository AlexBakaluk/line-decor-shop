package ru.linedecor.shop.repository.product.price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceTypeRepositoryTest {

    private static final int EXPECTED_TABLE_SIZE = 2;

    @Autowired
    private PriceTypeRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        PriceType sellPrice = new PriceType();
        sellPrice.setName("Цена продажи");

        PriceType wholesalePrice = new PriceType();
        wholesalePrice.setName("Оптовая цена");
        repository.save(sellPrice);
        repository.save(wholesalePrice);
    }

    @DisplayName("Should find all types and return its projections")
    @Test
    public void givenGetAllProjections_whenTableHasData_thenShouldReturnListDto() {
        assertThat(repository.getAllTypeViews())
                .hasSize(EXPECTED_TABLE_SIZE)
                .isInstanceOf(List.class)
                .hasOnlyElementsOfType(PriceTypeView.class);
    }

}