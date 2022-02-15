package ru.linedecore.shop.addressservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.linedecore.shop.addressservice.domain.City;
import ru.linedecore.shop.addressservice.dto.response.CityView;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(
            value = "select c.id as id, c.name as name " +
                    "from City c " +
                    "where upper(c.name) like concat(upper(:nameStarts), '%') " +
                    "and c.region.id = :regionId"
    )
    Slice<CityView> getViewsWhereNameStarts(Integer regionId, String nameStarts, Pageable pageable);

    @Query(
            value = "select c.id as id, c.name as name " +
                    "from City c " +
                    "where c.id = :id"
    )
    Optional<CityView> getViewById(Long id);
}
