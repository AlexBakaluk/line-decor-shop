package ru.linedecor.shop.service.product;

import ru.linedecor.shop.domain.dto.ProductDetailsDto;

public interface ProductDetailsService {

    ProductDetailsDto getProductDetailsWithCharacteristicsAndMeasureByProductId(long productId);

    ProductDetailsDto getFullProductDetails(long productId);
}
