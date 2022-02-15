package ru.linedecor.shop.service.product;

import ru.linedecor.shop.domain.dto.ProductDetailsView;
import ru.linedecor.shop.domain.product.ProductDetails;
import ru.linedecor.shop.dto.request.product.ProductDetailsDto;

public interface ProductDetailsService {

    ProductDetailsView getProductDetailsWithCharacteristicsAndMeasureByProductId(long productId);

    ProductDetailsView getFullProductDetails(long productId);

    ProductDetails createProductDetails(ProductDetailsDto details);
}
