package ru.linedecore.shop.addressservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.linedecore.shop.addressservice.domain.Region;
import ru.linedecore.shop.addressservice.dto.response.RegionView;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Query(
            value = "select r.id as id, r.name as name, r.code as code " +
                    "from Region r " +
                    "where r.country.id = :countryId")
    List<RegionView> getAllViewsByCountryId(Integer countryId);

    @Query(
            value = "select r.id as id, r.name as name, r.code as code " +
                    "from Region r " +
                    "join Country c on c = r.country " +
                    "where upper(c.shortName) = upper(:countryShortName)")
    List<RegionView> getAllViewsByCountryShortName(String countryShortName);

}
