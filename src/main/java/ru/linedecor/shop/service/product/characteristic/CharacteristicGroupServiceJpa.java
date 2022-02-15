package ru.linedecor.shop.service.product.characteristic;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.product.characteristic.CharacteristicGroupView;
import ru.linedecor.shop.domain.product.characteristic.CharacteristicGroup;
import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicGroupRequestDto;
import ru.linedecor.shop.exception.product.characteristic.CharacteristicGroupAlreadyExistsException;
import ru.linedecor.shop.exception.product.characteristic.CharacteristicGroupNotFoundException;
import ru.linedecor.shop.repository.product.characteristic.CharacteristicGroupRepository;
import ru.linedecor.shop.validation.EntityValidator;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CharacteristicGroupServiceJpa implements CharacteristicGroupService {

    private final CharacteristicGroupRepository groupRepository;
    private final EntityValidator<CharacteristicGroupRequestDto> groupValidator;

    @Transactional(readOnly = true)
    @Override
    public CharacteristicGroupView getGroupViewById(int id) {
        return findGroupViewByIdOrThrowException(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacteristicGroupView> getAllGroupsView() {
        return groupRepository.getAllViews();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacteristicGroupView> getAllGroupsViewByNameLike(String nameLike) {
        return groupRepository.getAllViewsByNameLikeIgnoreCase(nameLike);
    }

    @Override
    public CharacteristicGroup getGroupById(Integer groupId) {
        return findGroupByIdOrElseThrowException(groupId);
    }

    @Override
    public void createNewGroup(CharacteristicGroupRequestDto groupRequestDto) {
        String groupName = groupRequestDto.getName();
        groupValidator.validate(groupRequestDto);
        checkIsGroupNameUniqueOrElseThrowException(groupName);
        CharacteristicGroup newGroup = new CharacteristicGroup();
        BeanUtils.copyProperties(groupRequestDto, newGroup);
        groupRepository.save(newGroup);
    }

    @Override
    public void updateGroup(CharacteristicGroupRequestDto updatedGroup) {
        val groupId = updatedGroup.getId();
        Objects.requireNonNull(groupId, "For update id must not be null!");
        groupValidator.validate(updatedGroup);
        checkIsGroupNameUniqueOrElseThrowException(updatedGroup.getName());
        val groupFromDB = findGroupByIdOrElseThrowException(groupId);
        BeanUtils.copyProperties(updatedGroup, groupFromDB);
    }

    @Override
    public void deleteGroupById(int id) {
        val groupFromDB = findGroupByIdOrElseThrowException(id);
        groupRepository.delete(groupFromDB);
    }

    private CharacteristicGroup findGroupByIdOrElseThrowException(int id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new CharacteristicGroupNotFoundException(id));
    }

    private CharacteristicGroupView findGroupViewByIdOrThrowException(int id) {
        return groupRepository.getViewById(id)
                .orElseThrow(() -> new CharacteristicGroupNotFoundException(id));
    }

    private void checkIsGroupNameUniqueOrElseThrowException(String name) {
        if (groupRepository.existsByNameIgnoreCase(name)) {
            throw new CharacteristicGroupAlreadyExistsException(name);
        }
    }

}
