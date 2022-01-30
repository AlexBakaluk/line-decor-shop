package ru.linedecor.shop.service.product.price;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.dto.request.product.price.type.PriceTypeDto;
import ru.linedecor.shop.exception.product.price.PriceTypeAlreadyExistsException;
import ru.linedecor.shop.exception.product.price.PriceTypeNotFoundException;
import ru.linedecor.shop.repository.product.price.PriceTypeRepository;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
public class PriceTypeServiceJpa implements PriceTypeService {

    private PriceTypeRepository repository;
    private EntityValidator<PriceTypeDto> validator;

    @Transactional(readOnly = true)
    @Override
    public List<PriceTypeView> getAllTypeViews() {
        return repository.getAllTypeViews();
    }

    @Transactional(readOnly = true)
    @Override
    public PriceTypeView getTypeViewById(int id) {
        return repository.getViewById(id)
                .orElseThrow(throwPriceTypeNotFoundException(id));
    }

    @Override
    public void createNewPriceType(PriceTypeDto typeDto) {
        validator.validate(typeDto);
        checkIsNewTypeUniqueOrElseThrowException(typeDto);
        PriceType newType = new PriceType(typeDto);
        repository.save(newType);
    }



    @Override
    public void updateType(PriceTypeDto typeDto) {
        val typeId = typeDto.getId();
        Objects.requireNonNull(typeId, "Id must not be null for update!");
        checkIsNewTypeUniqueOrElseThrowException(typeDto);
        PriceType fromDB = getTypeByIdOrElseThrowException(typeId);
        BeanUtils.copyProperties(typeDto, fromDB);
    }

    private void checkIsNewTypeUniqueOrElseThrowException(PriceTypeDto typeDto) {
        val newTypeName = typeDto.getName();
        boolean isExists = repository.existsByNameIgnoreCase(newTypeName);
        if (isExists) {
            throw new PriceTypeAlreadyExistsException(newTypeName);
        }
    }


    @Override
    public void deleteTypeById(int id) {
        PriceType fromDB = getTypeByIdOrElseThrowException(id);
        repository.delete(fromDB);
    }

    private PriceType getTypeByIdOrElseThrowException(int id) {
        return repository.findById(id)
                .orElseThrow(throwPriceTypeNotFoundException(id));
    }

    private Supplier<PriceTypeNotFoundException> throwPriceTypeNotFoundException(int typeId) {
        return () -> new PriceTypeNotFoundException(typeId);
    }
}
