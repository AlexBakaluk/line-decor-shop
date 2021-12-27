package ru.linedecor.shop.controller.product.price;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.exception.product.price.PriceTypeAlreadyExistsException;
import ru.linedecor.shop.service.product.price.PriceTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/product/price/type", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceTypeController {

    private PriceTypeService typeService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<PriceTypeView> getAllTypes() {
        return typeService.getAllTypes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public PriceType createNewType(@RequestBody PriceType newType) {
            return typeService.createNewPriceType(newType);
    }

}
