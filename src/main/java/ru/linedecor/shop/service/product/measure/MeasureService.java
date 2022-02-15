package ru.linedecor.shop.service.product.measure;

import ru.linedecor.shop.domain.product.Measure;
import ru.linedecor.shop.dto.request.product.measure.MeasureRequestDto;
import ru.linedecor.shop.dto.response.product.measure.MeasureView;

import java.util.List;

public interface MeasureService {

    List<MeasureView> getAllMeasureViews();

    MeasureView getMeasureViewById(int id);

    void createNewMeasure(MeasureRequestDto newMeasure);

    void updateMeasure(MeasureRequestDto updated);

    void deleteMeasureById(int id);

    Measure getMeasureById(Integer measureId);
}
