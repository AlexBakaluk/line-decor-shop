package ru.linedecor.shop.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.ProductDetailsView;
import ru.linedecor.shop.domain.product.Measure;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.domain.product.ProductDetails;
import ru.linedecor.shop.dto.request.product.ProductDetailsDto;
import ru.linedecor.shop.service.product.brand.BrandService;
import ru.linedecor.shop.service.product.measure.MeasureService;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductDetailsServiceJpa implements ProductDetailsService {

    private final BrandService brandService;
    private final MeasureService measureService;

    @Override
    public ProductDetailsView getProductDetailsWithCharacteristicsAndMeasureByProductId(long productId) {
        return null;
    }

    @Override
    public ProductDetailsView getFullProductDetails(long productId) {
        return null;
    }

    @Override
    public ProductDetails createProductDetails(ProductDetailsDto details) {
        ProductDetails newDetails = new ProductDetails();
        ProductBrand brand = getBrandById(details.getBrandId());
        Measure measure = getMeasureById(details.getMeasureId());
        newDetails.setBrand(brand);
        newDetails.setMeasure(measure);
        newDetails.setDescription(details.getDescription());
        return newDetails;
    }

    private Measure getMeasureById(Integer measureId) {
        return measureService.getMeasureById(measureId);
    }

    private void copyDataFromDtoToNewDetails(ProductDetailsDto dto, ProductDetails newDetails) {

    }

    private ProductBrand getBrandById(Integer brandId) {
        return brandService.getBrandById(brandId);
    }
}
