package ru.linedecor.shop.controller.product.brand;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.service.product.brand.BrandService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductBrandController {

    private BrandService brandService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<ProductBrandView> getAll() {
        return brandService.getAllBrandViewsSortByName();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/id/{id}")
    public ProductBrandView getById(@PathVariable int id) {
        return brandService.getViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/name/{name}")
    public ProductBrandView getByName(@PathVariable String name) {
        return brandService.getViewByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNew(@RequestBody ProductBrand newBrand) {
        brandService.createNewBrand(newBrand);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/id/{id}")
    public void deleteBrand(@PathVariable int id) {
        brandService.deleteBrandById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/name/{name}")
    public void deleteBrand(@PathVariable String name) {
        brandService.deleteBrandByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateBrand(@RequestBody ProductBrand updatedBrand) {
        brandService.updateBrand(updatedBrand);
    }

}
