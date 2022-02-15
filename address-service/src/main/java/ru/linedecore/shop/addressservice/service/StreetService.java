package ru.linedecore.shop.addressservice.service;

import org.springframework.data.domain.Pageable;
import ru.linedecore.shop.addressservice.dto.request.StreetDto;
import ru.linedecore.shop.addressservice.dto.response.StreetView;

import java.util.List;

public interface StreetService {

    List<StreetView> getByCityIdWhereNameStarts(Long cityId, String nameStarts, Pageable pageable);

    StreetView getById(Long id);

    void createStreetAndAddToCity(StreetDto streetDto, Long cityId);
}
