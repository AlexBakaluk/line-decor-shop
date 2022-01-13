package ru.linedecor.shop.controller.product.brand;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.service.product.brand.BrandService;
import ru.linedecor.shop.validation.product.brand.BrandValidator;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductBrandController {

    private BrandService brandService;
    private BrandValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<ProductBrandView> getAll() {
        return brandService.getAllBrandViews();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/id/{id}")
    public ProductBrandView getById(@PathVariable int id) {
        return brandService.getViewById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductBrandView createNew(
            @Valid @RequestBody ProductBrand newBrand) {
        return brandService.createNewBrand(newBrand);
    }

}
