package ru.linedecor.shop.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.linedecor.shop.dto.response.product.ProductView;

public interface ProductService {

    Page<ProductView> getProductPage(Pageable pageable);

    Page<ProductView> getProductPageByNameOrSkuLike(String searchParam, Pageable pageable);

    void createProduct(ru.linedecor.shop.dto.request.product.ProductDto newProduct);

    void updateProductNameById(Long id, String newName);

    ProductView getProductWithDetails(Long id);

}
