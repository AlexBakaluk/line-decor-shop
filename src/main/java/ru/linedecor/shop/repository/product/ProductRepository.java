package ru.linedecor.shop.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.product.Product;
import ru.linedecor.shop.dto.response.product.ProductView;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p.id as id, p.name as name, p.sku as sku from Product p")
    Page<ProductView> getProductViewsPage(Pageable pageable);

    @Query(
            value = "select p.id as id, p.name as name, p.sku as sku " +
                    "from Product p " +
                    "where upper(p.name) like concat('%', upper(:nameOrSkuLike), '%') " +
                    "or upper(p.sku) like concat('%', upper(:nameOrSkuLike), '%')"
    )
    Page<ProductView> getProductViewsPageByNameOrSkuLike(String nameOrSkuLike, Pageable pageable);

    @Modifying
    @Query(
            value = "update Product p " +
                    "set p.name = :newName " +
                    "where p.id = :id")
    void updateProductNameById(Long id, String newName);

    @Query(
            value = "select p.id as id, p.name as name, p.sku as sku, d.description as description, m.name as measureName, b.name as brandName " +
                    "from Product p join p.details d join d.measure m join d.brand b " +
                    "where p.id = :id"
    )
    ProductView getProductDetailsViewById(Long id);

}
