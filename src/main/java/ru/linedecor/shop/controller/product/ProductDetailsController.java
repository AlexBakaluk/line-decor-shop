package ru.linedecor.shop.controller.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.ProductDetailsView;
import ru.linedecor.shop.service.product.ProductDetailsService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/product/details", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductDetailsController {

    private ProductDetailsService detailsService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/characteristicsAndMeasure/{productId}")
    public ProductDetailsView getDetailsWithCharacteristicsAndMeasureByProductId(@PathVariable Long productId) {
        return detailsService.getProductDetailsWithCharacteristicsAndMeasureByProductId(productId);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/full/{productId}")
    public ProductDetailsView getFullDetailsByProductId(@PathVariable Long productId) {
        return detailsService.getFullProductDetails(productId);
    }

}
