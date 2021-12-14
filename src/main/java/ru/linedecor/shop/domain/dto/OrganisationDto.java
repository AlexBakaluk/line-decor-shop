package ru.linedecor.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.linedecor.shop.domain.Warehouse;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface OrganisationDto {

    Long getId();
    String getName();
    List<WarehouseDto> getWarehouses();

}
