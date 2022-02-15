package ru.linedecor.shop.service.product.characteristic;

import ru.linedecor.shop.domain.dto.product.characteristic.CharacteristicGroupView;
import ru.linedecor.shop.domain.product.characteristic.CharacteristicGroup;
import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicGroupRequestDto;

import java.util.List;

public interface CharacteristicGroupService {

    void createNewGroup(CharacteristicGroupRequestDto newGroup);

    List<CharacteristicGroupView> getAllGroupsView();

    CharacteristicGroupView getGroupViewById(int id);

    void deleteGroupById(int id);

    void updateGroup(CharacteristicGroupRequestDto updatedGroup);

    List<CharacteristicGroupView> getAllGroupsViewByNameLike(String nameLike);

    CharacteristicGroup getGroupById(Integer groupId);
}
