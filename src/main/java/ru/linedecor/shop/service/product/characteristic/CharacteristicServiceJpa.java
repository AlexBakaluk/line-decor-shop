package ru.linedecor.shop.service.product.characteristic;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.product.characteristic.Characteristic;
import ru.linedecor.shop.domain.product.characteristic.CharacteristicType;
import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicRequestDto;
import ru.linedecor.shop.dto.response.product.characteristic.CharacteristicView;
import ru.linedecor.shop.exception.product.characteristic.CharacteristicNotFoundException;
import ru.linedecor.shop.repository.product.characteristic.CharacteristicRepository;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacteristicServiceJpa implements CharacteristicService{

    private final CharacteristicRepository characteristicRepository;
    private final CharacteristicGroupService groupService;
    private final EntityValidator<CharacteristicRequestDto> characteristicValidator;

    @Transactional(readOnly = true)
    @Override
    public CharacteristicView getCharacteristicViewById(long id) {
        checkIsCharacteristicWithIdExistsOrElseThrowException(id);
        return characteristicRepository.getViewById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacteristicView> getAllCharacteristicViews() {
        return characteristicRepository.getAllViews();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacteristicView> getAllCharacteristicViewsWhereNameLike(String nameLike) {
        return characteristicRepository.getAllViewsWhereNameLike(nameLike);
    }

    @Override
    public void createNewCharacteristic(CharacteristicRequestDto newCharacteristicDto) {
        characteristicValidator.validate(newCharacteristicDto);
        Characteristic newCharacteristic = new Characteristic();
        newCharacteristic.setName(newCharacteristicDto.getName());
        newCharacteristic.setType(CharacteristicType.getTypeFromName(newCharacteristicDto.getType()));
        val group = groupService.getGroupById(newCharacteristicDto.getGroupId());
        newCharacteristic.setCharacteristicGroup(group);
        characteristicRepository.save(newCharacteristic);
    }

    @Override
    public void updateCharacteristic(CharacteristicRequestDto updated) {
        val id = updated.getId();
        Objects.requireNonNull(id, "Id must not be null for update!");
        val fromDB = findCharacteristicByIdOrElseThrowException(id);
        BeanUtils.copyProperties(updated, fromDB);
    }

    @Override
    public void deleteCharacteristicById(long id) {
        val characteristicFromDB = findCharacteristicByIdOrElseThrowException(id);
        characteristicRepository.delete(characteristicFromDB);
    }

    private void checkIsCharacteristicWithIdExistsOrElseThrowException(long id) {
        if (!characteristicRepository.existsById(id)) {
            throw new CharacteristicNotFoundException(id);
        }
    }

    private Characteristic findCharacteristicByIdOrElseThrowException(long id) {
        return characteristicRepository.findById(id)
                .orElseThrow(() -> new CharacteristicNotFoundException(id));
    }

}
