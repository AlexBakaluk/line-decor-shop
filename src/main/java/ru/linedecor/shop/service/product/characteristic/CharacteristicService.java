package ru.linedecor.shop.service.product.characteristic;

import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicRequestDto;
import ru.linedecor.shop.dto.response.product.characteristic.CharacteristicView;

import java.util.List;

public interface CharacteristicService {

    void createNewCharacteristic(CharacteristicRequestDto newCharacteristic);

    void deleteCharacteristicById(long id);

    List<CharacteristicView> getAllCharacteristicViews();

    CharacteristicView getCharacteristicViewById(long id);

    List<CharacteristicView> getAllCharacteristicViewsWhereNameLike(String nameLike);

    void updateCharacteristic(CharacteristicRequestDto updated);
}
