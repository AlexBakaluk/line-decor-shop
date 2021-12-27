package ru.linedecor.shop.domain.product;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_price")
public class ProductPrice {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "price_type_id")
    private PriceType priceType;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "product_details_id")
    private ProductDetails productDetails;

}
