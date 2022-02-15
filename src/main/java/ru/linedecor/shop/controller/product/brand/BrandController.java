package ru.linedecor.shop.controller.product.brand;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.dto.request.product.brand.BrandDto;
import ru.linedecor.shop.service.product.brand.BrandService;

import java.util.List;

@CrossOrigin(value = "*")
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {

    private BrandService brandService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductBrandView> getAll() {
        return brandService.getAllBrandViewsSortByName();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{id}")
    public ProductBrandView getById(@PathVariable int id) {
        return brandService.getViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ProductBrandView getByName(@PathVariable String name) {
        return brandService.getViewByName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNew(@RequestBody BrandDto newBrand) {
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
    public void updateBrand(@RequestBody BrandDto updatedBrand) {
        brandService.updateBrand(updatedBrand);
    }

}
