package ru.linedecore.shop.addressservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.linedecore.shop.addressservice.domain.Street;
import ru.linedecore.shop.addressservice.dto.response.StreetView;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Long> {

    @Query(
            value = "select s.id as id, s.name as name " +
                    "from Street s " +
                    "where s.city.id = :cityId " +
                    "and upper(s.name) like concat(upper(:nameStarts), '%')"
    )
    Slice<StreetView> getSliceByCityId(Long cityId, String nameStarts, Pageable pageable);

    @Query(
            value = "select s.id as id, s.name as name " +
                    "from Street s " +
                    "where s.id = :id "
    )
    Optional<StreetView> getViewById(Long id);
}
