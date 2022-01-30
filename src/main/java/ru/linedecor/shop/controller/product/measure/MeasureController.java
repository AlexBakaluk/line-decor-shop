package ru.linedecor.shop.controller.product.measure;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.dto.request.product.measure.MeasureRequestDto;
import ru.linedecor.shop.dto.response.product.measure.MeasureView;
import ru.linedecor.shop.service.product.measure.MeasureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/product/measure")
public class MeasureController {

    private final MeasureService measureService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/id/{id}")
    public MeasureView getById(@PathVariable int id) {
        return measureService.getMeasureViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<MeasureView> getAll() {
        return measureService.getAllMeasureViews();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMeasure(@RequestBody MeasureRequestDto newMeasure) {
        measureService.createNewMeasure(newMeasure);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateMeasure(@RequestBody MeasureRequestDto updated) {
        measureService.updateMeasure(updated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/id/{id}")
    public void deleteById(@PathVariable int id) {
        measureService.deleteMeasureById(id);
    }

}
