package ru.linedecor.shop.integration.product.brand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.repository.product.brand.BrandRepository;
import ru.linedecor.shop.service.product.brand.BrandService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BrandIntegrationTest {

    private static final String BASE_URL = "/api/brand";
    private static final int EXPECTED_TABLE_SIZE = 5;

    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    public void setUp() {
        brandRepository.save(new ProductBrand("Line decor"));
        brandRepository.save(new ProductBrand("Line of Decor"));
        brandRepository.save(new ProductBrand("Art line"));
        brandRepository.save(new ProductBrand("Decorazza"));
        brandRepository.save(new ProductBrand("Decor line"));
        assertThat(brandRepository.count())
                .isEqualTo(EXPECTED_TABLE_SIZE);
    }

    @AfterEach
    public void tearDown() {
        brandRepository.deleteAll();
        assertThat(brandRepository.count())
                .isEqualTo(0);
    }

    @Test
    public void givenGetAllBrandsWithoutParams_ShouldReturnAllBrandsSortedByName() {

    }

}
