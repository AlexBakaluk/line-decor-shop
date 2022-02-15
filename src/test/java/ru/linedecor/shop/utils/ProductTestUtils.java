package ru.linedecor.shop.utils;

import lombok.val;
import org.springframework.data.domain.*;
import ru.linedecor.shop.domain.dto.ProductCharacteristicDto;
import ru.linedecor.shop.domain.dto.ProductDetailsView;
import ru.linedecor.shop.domain.dto.ProductMeasureDto;
import ru.linedecor.shop.domain.dto.ProductPriceDto;
import ru.linedecor.shop.dto.response.product.ProductView;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductTestUtils {

    private static final int TOTAL_PRODUCT_TABLE_SIZE = 100;
    private static final String testImagePath = "classpath:ru/linedecor/shop/utils/ProductTestUtils.java";
    private static final File testImage = new File("classpath:ru/linedecor/shop/utils/ProductTestUtils.java");

    public static int getTotalProductTableSize() {
        return TOTAL_PRODUCT_TABLE_SIZE;
    }

    public static Page<ProductView> getTestPage(int pageNumber, int pageSize, Sort sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortBy);
        var testProductList = fillListWithTestData(pageNumber, pageSize);
        return new PageImpl<>(testProductList, pageable, TOTAL_PRODUCT_TABLE_SIZE);
    }

    private static List<ProductView> fillListWithTestData(int pageNumber, int pageSize) {
        List<ProductView> testProductList = new ArrayList<>(pageSize);
        int endNumber = (pageNumber + 1) * pageSize;
        for (int i = pageNumber * pageSize; i < endNumber && i < TOTAL_PRODUCT_TABLE_SIZE; i++) {
            var generatedView = generateProductView(i);
            testProductList.add(generatedView);
        }
        return testProductList;
    }


    private static ProductView generateProductView(int productId) {
//        return new ProductView() {
//            @Override
//            public Long getId() {
//                return (long) productId;
//            }
//
//            @Override
//            public String getName() {
//                return "Test product " + productId;
//            }
//
//            @Override
//            public String getSku() {
//                return "test sku " + productId;
//            }
//        };
        return null;
    }

    public static Page<ProductView> getAllWhereNameLike(int pageNumber, int pageSize, String nameOrSku) {
        val allProds = generateProductViewListWithSize(TOTAL_PRODUCT_TABLE_SIZE);
        val filteredProds = allProds
                .stream()
                .filter(e -> e.getName().contains(nameOrSku) || e.getSku().contains(nameOrSku))
                .collect(Collectors.toList());
        val totalFilteredElementsCount = filteredProds.size();
        val filteredProdsWithLimit = filteredProds.stream().limit(pageSize).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return new PageImpl<>(filteredProdsWithLimit, pageable, totalFilteredElementsCount);
    }

    private static List<ProductView> generateProductViewListWithSize(int size) {
        val views = new ArrayList<ProductView>(size);
        for (int i = 0; i < size; i++) {
            views.add(generateProductView(i));
        }
        return views;
    }

    public static ProductDetailsView createTestProductDetailsWithCharacteristicsAndMeasure() {
        String description = createProductDescription();
        return new ProductDetailsView(1L, description, createProductMeasureDto(), null, createCharacteristicsDto());
    }

    public static ProductDetailsView createTestProductFullDetails() {
        String description = createProductDescription();
        return new ProductDetailsView(1L, description, createProductMeasureDto(), createProductPricesDto(), createCharacteristicsDto());
    }


    private static String createProductDescription() {
        return "Very long product description";
    }

    private static ProductMeasureDto createProductMeasureDto() {
        return new ProductMeasureDto(22, "PC");
    }

    private static Set<ProductCharacteristicDto> createCharacteristicsDto() {
        Set<ProductCharacteristicDto> characteristicDtoList = new HashSet<>(5);
        characteristicDtoList.add(new ProductCharacteristicDto(1L, "Root color", "Black, white"));
        characteristicDtoList.add(new ProductCharacteristicDto(2L, "Additional color", "Yellow"));
        characteristicDtoList.add(new ProductCharacteristicDto(3L, "Weight, kg.", "0.17"));
        characteristicDtoList.add(new ProductCharacteristicDto(4L, "Size (length x width)", "45 x 60"));
        characteristicDtoList.add(new ProductCharacteristicDto(5L, "Material", "Cotton 100%"));
        return characteristicDtoList;
    }

    private static List<ProductPriceDto> createProductPricesDto() {
        List<ProductPriceDto> priceDtoList = new ArrayList<>(5);
        priceDtoList.add(new ProductPriceDto(1L, "Sell price", new BigDecimal(750)));
        priceDtoList.add(new ProductPriceDto(1L, "Wholesale price", new BigDecimal(550)));
        priceDtoList.add(new ProductPriceDto(1L, "Purchase price", new BigDecimal(343)));
        priceDtoList.add(new ProductPriceDto(1L, "Marketplace price", new BigDecimal(1124)));
        priceDtoList.add(new ProductPriceDto(1L, "Sale price", new BigDecimal(678)));
        return priceDtoList;
    }
}
