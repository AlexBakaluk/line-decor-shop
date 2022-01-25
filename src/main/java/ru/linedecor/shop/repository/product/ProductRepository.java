package ru.linedecor.shop.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.linedecor.shop.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
