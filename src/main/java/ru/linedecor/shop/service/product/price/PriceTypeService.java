package ru.linedecor.shop.service.product.price;

import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;

import java.util.List;

public interface PriceTypeService {

    List<PriceTypeView> getAllTypes();

    PriceType createNewPriceType(PriceType newType);

}
