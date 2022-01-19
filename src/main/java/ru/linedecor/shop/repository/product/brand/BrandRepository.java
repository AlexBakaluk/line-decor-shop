package ru.linedecor.shop.repository.product.brand;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<ProductBrand, Integer> {

    @Query(value = "select b.id as id, b.name as name from ProductBrand b")
    List<ProductBrandView> getAllViews(Sort sort);

    @Query(value = "select b.id as id, b.name as name from ProductBrand b where b.id = :id")
    Optional<ProductBrandView> getViewById(@Param("id") int id);

    @Query(value = "select b.id as id, b.name as name from ProductBrand b where b.name = :name")
    Optional<ProductBrandView> getViewByName(@Param("name") String name);

    boolean existsByName(String name);

    Optional<ProductBrand> findByName(String name);
}
