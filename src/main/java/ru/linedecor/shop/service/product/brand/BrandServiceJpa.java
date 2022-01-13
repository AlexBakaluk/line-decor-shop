package ru.linedecor.shop.service.product.brand;

import org.springframework.stereotype.Service;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;

import java.util.List;

@Service
public class BrandServiceJpa implements BrandService {

    @Override
    public List<ProductBrandView> getAllBrandViews() {
        return null;
    }

    @Override
    public ProductBrandView getViewById(int anyInt) {
        return null;
    }

    @Override
    public ProductBrandView createNewBrand(ProductBrand newBrand) {
        return null;
    }
}
