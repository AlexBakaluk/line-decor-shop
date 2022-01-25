package ru.linedecor.shop.controller.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.ProductView;
import ru.linedecor.shop.domain.product.Product;
import ru.linedecor.shop.service.product.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public Page<ProductView> getProductPage(
            @PageableDefault(size = 20, sort = {"name"}) Pageable pageable) {
        return productService.getProductPage(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/nameOrSku/{nameOrSku}")
    public Page<ProductView> getProductPageByNameLike(
            @PageableDefault(size = 20, sort = {"name"}) Pageable pageable,
            @PathVariable String nameOrSku) {
        return productService.getProductPageByNameOrSkuLike(nameOrSku, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createProduct(@RequestBody Product newProduct) {
        productService.createProduct(newProduct);
    }

}
