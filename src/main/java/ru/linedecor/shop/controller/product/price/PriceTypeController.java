package ru.linedecor.shop.controller.product.price;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.dto.request.product.price.type.PriceTypeDto;
import ru.linedecor.shop.service.product.price.PriceTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/product/price/type")
public class PriceTypeController {

    private PriceTypeService typeService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/id/{id}")
    public PriceTypeView getTypeById(@PathVariable int id) {
        return typeService.getTypeViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<PriceTypeView> getAllTypes() {
        return typeService.getAllTypeViews();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public void createNewType(@RequestBody PriceTypeDto newType) {
        typeService.createNewPriceType(newType);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateType(@RequestBody PriceTypeDto updated) {
        typeService.updateType(updated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/id/{id}")
    public void deleteTypeById(@PathVariable int id) {
        typeService.deleteTypeById(id);
    }

}
