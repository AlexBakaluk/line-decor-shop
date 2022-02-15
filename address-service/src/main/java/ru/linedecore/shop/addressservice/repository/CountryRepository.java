package ru.linedecore.shop.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.linedecore.shop.addressservice.domain.Country;
import ru.linedecore.shop.addressservice.dto.response.CountryView;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "select c.id as id, c.shortName as shortName, c.fullName as fullName from Country c")
    List<CountryView> getAllViews();

    @Query(value = "select c.id as id, c.shortName as shortName, c.fullName as fullName from Country c where c.id = :id")
    Optional<CountryView> getViewById(Integer id);

}
