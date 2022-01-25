package ru.linedecor.shop.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.ProductView;
import ru.linedecor.shop.domain.product.Product;
import ru.linedecor.shop.repository.product.ProductRepository;
import ru.linedecor.shop.validation.EntityValidator;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceJpa implements ProductService{

    private final ProductRepository productRepository;
    private final EntityValidator<Product> productValidator;

    @Override
    public Page<ProductView> getProductPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductView> getProductPageByNameOrSkuLike(String nameLike, Pageable pageable) {
        return null;
    }

    @Override
    public void createProduct(Product newProduct) {
        productValidator.validate(newProduct);
    }
}
