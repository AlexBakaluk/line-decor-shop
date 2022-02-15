package ru.linedecor.shop.repository.product.characteristic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.dto.product.characteristic.CharacteristicGroupView;
import ru.linedecor.shop.domain.product.characteristic.CharacteristicGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicGroupRepository extends JpaRepository<CharacteristicGroup, Integer> {

    boolean existsByNameIgnoreCase(String name);

    @Query(value = "select g.id as id, g.name as name from CharacteristicGroup g")
    List<CharacteristicGroupView> getAllViews();

    @Query(value = "select g.id as id, g.name as name from CharacteristicGroup g where upper(name) like upper (concat('%', :nameLike, '%'))")
    List<CharacteristicGroupView> getAllViewsByNameLikeIgnoreCase(String nameLike);

    @Query(value = "select g.id as id, g.name as name from CharacteristicGroup g where g.id = :id")
    Optional<CharacteristicGroupView> getViewById(int id);
}
