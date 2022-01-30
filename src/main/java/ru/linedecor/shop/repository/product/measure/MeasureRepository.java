package ru.linedecor.shop.repository.product.measure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.product.Measure;
import ru.linedecor.shop.dto.response.product.measure.MeasureView;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

    @Query(value = "select m.id as id, m.name as name from Measure m")
    List<MeasureView> getAllViews();

    @Query(value = "select m.id as id, m.name as name from Measure m where m.id = :id")
    Optional<MeasureView> getViewById(Integer id);

    boolean existsByNameIgnoreCase(String name);

}
