package ru.linedecor.shop.service.product.brand;

import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;

import java.util.List;

public interface BrandService {
    List<ProductBrandView> getAllBrandViews();

    ProductBrandView getViewById(int anyInt);

    ProductBrandView createNewBrand(ProductBrand newBrand);
}
