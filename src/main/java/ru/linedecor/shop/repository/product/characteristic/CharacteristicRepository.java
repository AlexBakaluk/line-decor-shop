package ru.linedecor.shop.repository.product.characteristic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.product.characteristic.Characteristic;
import ru.linedecor.shop.dto.response.product.characteristic.CharacteristicView;

import java.util.List;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

    boolean existsById(long id);

    @Query(value = "select c.id as id, c.name as name, c.type as type, g.name as groupName " +
            "from Characteristic c join c.characteristicGroup g")
    List<CharacteristicView> getAllViews();

    @Query(value = "select c.id as id, c.name as name, c.type as type, g.name as groupName " +
            "from Characteristic c join c.characteristicGroup g " +
            "where c.id = :id")
    CharacteristicView getViewById(long id);

    @Query(value = "select c.id as id, c.name as name, c.type as type, g.name as groupName " +
            "from Characteristic c " +
            "join c.characteristicGroup g " +
            "where upper(c.name) like upper (concat('%', :nameLike, '%'))")
    List<CharacteristicView> getAllViewsWhereNameLike(String nameLike);
}
