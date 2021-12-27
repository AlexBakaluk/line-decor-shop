package ru.linedecor.shop.service.product;

import org.springframework.stereotype.Service;
import ru.linedecor.shop.domain.dto.ProductDetailsDto;

@Service
public class ProductDetailsServiceJpa implements ProductDetailsService {
    @Override
    public ProductDetailsDto getProductDetailsWithCharacteristicsAndMeasureByProductId(long anyLong) {
        return null;
    }

    @Override
    public ProductDetailsDto getFullProductDetails(long productId) {
        return null;
    }
}
