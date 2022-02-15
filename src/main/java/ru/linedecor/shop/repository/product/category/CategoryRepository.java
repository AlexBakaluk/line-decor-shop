package ru.linedecor.shop.repository.product.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.product.Category;
import ru.linedecor.shop.dto.response.product.category.CategoryView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(
            value = "select c.id as id, c.name as name " +
                    "from Category c " +
                    "where c.id = :id")
    Optional<CategoryView> getViewById(int id);

    @Query(
            value = "select c.id as id, c.name as name " +
                    "from Category c")
    List<CategoryView> getAllCategoryViews();

    @Query(
            value = "select c.id as id, c.name as name " +
                    "from Category c " +
                    "where upper(c.name) like concat('%', upper(:nameLike), '%')")
    List<CategoryView> getAllCategoryViewsWhereNameLike(String nameLike);

    @Query(
            value = "select c.id, c.name from category c " +
                    "left join products_categories pc on c.id = pc.category_id " +
                    "where pc.product_id = :productId",
            nativeQuery = true
    )
    List<CategoryView> getAllByProductId(Long productId);

    Set<Category> findAllByIdIn(Set<Integer> ids);

    boolean existsByNameIgnoreCase(String name);

}
