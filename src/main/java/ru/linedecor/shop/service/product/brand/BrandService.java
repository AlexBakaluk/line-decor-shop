package ru.linedecor.shop.service.product.brand;

import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;

import java.util.List;

public interface BrandService {

    List<ProductBrandView> getAllBrandViewsSortByName();

    ProductBrandView getViewById(int id);

    ProductBrandView getViewByName(String name);

    void createNewBrand(ProductBrand newBrand);

    void updateBrand(ProductBrand updated);

    void deleteBrandById(int id);

    void deleteBrandByName(String name);

}
