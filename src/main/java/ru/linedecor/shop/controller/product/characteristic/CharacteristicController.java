package ru.linedecor.shop.controller.product.characteristic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.product.characteristic.CharacteristicGroupView;
import ru.linedecor.shop.domain.product.CharacteristicGroup;
import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicRequestDto;
import ru.linedecor.shop.dto.response.product.characteristic.CharacteristicView;
import ru.linedecor.shop.service.product.characteristic.CharacteristicService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/product/characteristic")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/id/{id}")
    public CharacteristicView getCharacteristicById(@PathVariable long id) {
        return characteristicService.getCharacteristicViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<CharacteristicView> getAllCharacteristics() {
        return characteristicService.getAllCharacteristicViews();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/nameLike/{nameLike}")
    public List<CharacteristicView> getAllCharacteristicsWhereNameLike(@PathVariable String nameLike) {
        return characteristicService.getAllCharacteristicViewsWhereNameLike(nameLike);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewCharacteristic(@RequestBody CharacteristicRequestDto newCharacteristic) {
        characteristicService.createNewCharacteristic(newCharacteristic);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateCharacteristic(@RequestBody CharacteristicRequestDto updated) {
        characteristicService.updateCharacteristic(updated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/id/{id}")
    public void deleteCharacteristicById(@PathVariable int id) {
        characteristicService.deleteCharacteristicById(id);
    }



}
