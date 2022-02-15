package ru.linedecor.shop.service.product.measure;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.product.Measure;
import ru.linedecor.shop.dto.request.product.measure.MeasureRequestDto;
import ru.linedecor.shop.dto.response.product.measure.MeasureView;
import ru.linedecor.shop.exception.product.measure.MeasureAlreadyExistsException;
import ru.linedecor.shop.exception.product.measure.MeasureNotFoundException;
import ru.linedecor.shop.repository.product.measure.MeasureRepository;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
@Transactional
public class MeasureServiceJpa implements MeasureService {

    private final MeasureRepository measureRepository;
    private final EntityValidator<MeasureRequestDto> validator;

    @Transactional(readOnly = true)
    @Override
    public List<MeasureView> getAllMeasureViews() {
        return measureRepository.getAllViews();
    }

    @Transactional(readOnly = true)
    @Override
    public MeasureView getMeasureViewById(int id) {
        return measureRepository.getViewById(id)
                .orElseThrow(throwMeasureNotFoundException(id));
    }


    @Override
    public void createNewMeasure(MeasureRequestDto requestMeasure) {
        validator.validate(requestMeasure);
        checkIsNewMeasureUniqueOrElseThrowException(requestMeasure);
        Measure newMeasure = new Measure(requestMeasure);
        measureRepository.save(newMeasure);
    }

    private void checkIsNewMeasureUniqueOrElseThrowException(MeasureRequestDto newMeasure) {
        val measureName = newMeasure.getName();
        boolean isExists = measureRepository.existsByNameIgnoreCase(measureName);
        if (isExists) {
            throw new MeasureAlreadyExistsException(measureName);
        }
    }

    @Override
    public void updateMeasure(MeasureRequestDto updated) {
        val measureId = updated.getId();
        Objects.requireNonNull(measureId, "Id must not be null for update!");
        validator.validate(updated);
        Measure fromDB = findByIdOrElseThrowException(measureId);
        BeanUtils.copyProperties(updated, fromDB);
    }

    @Override
    public void deleteMeasureById(int id) {
        Measure forDelete = findByIdOrElseThrowException(id);
        measureRepository.delete(forDelete);
    }

    @Transactional(readOnly = true)
    @Override
    public Measure getMeasureById(Integer measureId) {
        return findByIdOrElseThrowException(measureId);
    }

    private Measure findByIdOrElseThrowException(int id) {
        return measureRepository.findById(id)
                .orElseThrow(throwMeasureNotFoundException(id));
    }

    private Supplier<MeasureNotFoundException> throwMeasureNotFoundException(int id) {
        return () -> new MeasureNotFoundException(id);
    }
}
