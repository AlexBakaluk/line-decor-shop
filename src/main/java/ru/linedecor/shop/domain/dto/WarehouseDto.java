package ru.linedecor.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class WarehouseDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;

}
