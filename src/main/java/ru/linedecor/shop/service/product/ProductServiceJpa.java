package ru.linedecor.shop.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.product.Category;
import ru.linedecor.shop.domain.product.Product;
import ru.linedecor.shop.domain.product.ProductDetails;
import ru.linedecor.shop.dto.response.product.ProductView;
import ru.linedecor.shop.repository.product.ProductRepository;
import ru.linedecor.shop.service.product.category.CategoryService;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.Objects;
import java.util.Set;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceJpa implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDetailsService detailsService;
    private final CategoryService categoryService;
    private final EntityValidator<ru.linedecor.shop.dto.request.product.ProductDto> productValidator;

    @Transactional(readOnly = true)
    @Override
    public Page<ProductView> getProductPage(Pageable pageable) {
        return productRepository.getProductViewsPage(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductView> getProductPageByNameOrSkuLike(String nameOrSkuLike, Pageable pageable) {
        return productRepository.getProductViewsPageByNameOrSkuLike(nameOrSkuLike, pageable);
    }

    @Override
    public void createProduct(ru.linedecor.shop.dto.request.product.ProductDto productDto) {
        Product newProduct = new Product();
        copyDtoDataToNewProduct(productDto, newProduct);
        ProductDetails details = detailsService.createProductDetails(productDto.getDetails());
        Set<Category> categories = categoryService.getCategoriesByIds(productDto.getCategoryIds());
        newProduct.setDetails(details);
        newProduct.setCategories(categories);
        productRepository.save(newProduct);
    }

    @Override
    public void updateProductNameById(Long id, String newName) {
        Objects.requireNonNull(id, "Id must not be null for update");
        productRepository.updateProductNameById(id, newName);
    }

    @Override
    public ProductView getProductWithDetails(Long id) {
        return productRepository.getProductDetailsViewById(id);
    }

    private void copyDtoDataToNewProduct(ru.linedecor.shop.dto.request.product.ProductDto dto, Product newProduct) {
        newProduct.setName(dto.getName());
        newProduct.setSku(dto.getSku());
    }
}
