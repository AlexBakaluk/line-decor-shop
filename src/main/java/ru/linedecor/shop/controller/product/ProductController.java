package ru.linedecor.shop.controller.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.dto.response.product.ProductView;
import ru.linedecor.shop.service.product.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public Page<ProductView> getProductPage(
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ru.linedecor.shop.dto.request.product.ProductDto newProduct) {
        productService.createProduct(newProduct);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/id/{id}/name/{newName}")
    public void updateProductName(@PathVariable Long id, @PathVariable String newName) {
        productService.updateProductNameById(id, newName);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/fullDetails/id/{id}")
    public ProductView getProductWithDetails(@PathVariable Long id) {
        return productService.getProductWithDetails(id);
    }

}
