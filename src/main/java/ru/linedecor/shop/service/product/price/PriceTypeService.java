package ru.linedecor.shop.service.product.price;

import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.dto.request.product.price.type.PriceTypeDto;

import java.util.List;

public interface PriceTypeService {

    List<PriceTypeView> getAllTypeViews();

    void createNewPriceType(PriceTypeDto newType);

    PriceTypeView getTypeViewById(int id);

    void updateType(PriceTypeDto updated);

    void deleteTypeById(int id);
}
