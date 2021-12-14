package ru.linedecor.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.linedecor.shop.domain.Organisation;
import ru.linedecor.shop.domain.dto.OrganisationDto;

import java.util.List;
import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    @Query("select o.id, o.name from Organisation o")
    List<OrganisationDto> getAllOrganisationProjections();

    @Query("select o.id, o.name from Organisation o where o.id = :id")
    Optional<OrganisationDto> getOrganisationProjectionById(Long id);
}
