package ru.linedecor.shop.repository.product.price;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.PriceTypeView;
import ru.linedecor.shop.domain.product.PriceType;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PriceTypeRepository extends CrudRepository<PriceType, Integer> {

    @Query(value = "select pt.id as id, pt.name as name from PriceType pt")
    List<PriceTypeView> getAllTypeViews();

    @Query(value = "select pt.id as id, pt.name as name from PriceType pt where pt.id = :id")
    Optional<PriceTypeView> getViewById(Integer id);

    boolean existsByNameIgnoreCase(String name);
}
