package ru.linedecor.shop.service.product.brand;

import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.dto.request.product.brand.BrandDto;

import java.util.List;

public interface BrandService {

    List<ProductBrandView> getAllBrandViewsSortByName();

    ProductBrandView getViewById(int id);

    ProductBrandView getViewByName(String name);

    ProductBrand getBrandById(int id);

    void createNewBrand(BrandDto newBrand);

    void updateBrand(BrandDto updated);

    void deleteBrandById(int id);

    void deleteBrandByName(String name);

}
