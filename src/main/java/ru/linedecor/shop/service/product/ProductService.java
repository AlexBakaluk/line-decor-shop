package ru.linedecor.shop.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.linedecor.shop.domain.dto.ProductView;

public interface ProductService {

    Page<ProductView> getProductPage(Pageable pageable);

    Page<ProductView> getProductPageByNameOrSkuLike(String searchParam, Pageable pageable);
}
