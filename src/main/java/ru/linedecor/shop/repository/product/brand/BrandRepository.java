package ru.linedecor.shop.repository.product.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.linedecor.shop.domain.product.ProductBrand;

@Repository
public interface BrandRepository extends JpaRepository<ProductBrand, Integer> {
}
