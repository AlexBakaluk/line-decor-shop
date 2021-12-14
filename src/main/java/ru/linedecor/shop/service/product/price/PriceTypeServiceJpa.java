package ru.linedecor.shop.service.product.price;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;
import ru.linedecor.shop.exception.product.price.PriceTypeAlreadyExistsException;
import ru.linedecor.shop.exception.product.price.PriceTypesTableIsEmptyException;
import ru.linedecor.shop.repository.product.price.PriceTypeRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class PriceTypeServiceJpa implements PriceTypeService {

    private PriceTypeRepository repository;

    @Transactional(readOnly = true)
    public List<PriceTypeView> getAllTypes() {
        var allTypes = repository.getAllTypesProjections();
        if (allTypes.size() == 0) {
            throw new PriceTypesTableIsEmptyException();
        } else {
            return allTypes;
        }
    }

    @Transactional
    public PriceType createNewPriceType(PriceType newType) {
        try {
            return repository.save(newType);
        } catch (DataIntegrityViolationException e) {
            throw new PriceTypeAlreadyExistsException(newType.getName());
        }
    }
}
