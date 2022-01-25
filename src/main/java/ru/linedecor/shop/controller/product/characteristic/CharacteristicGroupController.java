package ru.linedecor.shop.controller.product.characteristic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.domain.dto.product.characteristic.CharacteristicGroupView;
import ru.linedecor.shop.domain.product.CharacteristicGroup;
import ru.linedecor.shop.dto.request.product.characteristic.CharacteristicGroupRequestDto;
import ru.linedecor.shop.service.product.characteristic.CharacteristicGroupService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/product/characteristic/group")
public class CharacteristicGroupController {

    private final CharacteristicGroupService groupService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CharacteristicGroupView> getAllGroups() {
        return groupService.getAllGroupsView();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(
            path = "/nameLike/{nameLike}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CharacteristicGroupView> getAllGroupsByNameLikeIgnoreCase(@PathVariable String nameLike) {
        return groupService.getAllGroupsViewByNameLike(nameLike);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(
            path = "/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CharacteristicGroupView getGroupById(@PathVariable int id) {
        return groupService.getGroupViewById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCharacteristicGroup(@RequestBody CharacteristicGroupRequestDto newGroup) {
        groupService.createNewGroup(newGroup);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGroup(@RequestBody CharacteristicGroupRequestDto updatedGroup) {
        groupService.updateGroup(updatedGroup);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/id/{id}")
    public void deleteGroupById(@PathVariable int id) {
        groupService.deleteGroupById(id);
    }

}
