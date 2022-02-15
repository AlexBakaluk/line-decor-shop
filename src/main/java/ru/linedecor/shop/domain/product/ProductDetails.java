package ru.linedecor.shop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.linedecor.shop.domain.product.characteristic.ProductsCharacteristics;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @Column(name = "product_id")
    private Long id;

    private String description;

    @MapsId
    @OneToOne
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "measure_id")
    private Measure measure;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "productDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductPrice> prices = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "details",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductsCharacteristics> characteristics = new ArrayList<>();

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductBrand brand;

    public void addPrice(ProductPrice price) {
        prices.add(price);
        price.setProductDetails(this);
    }

    public void removePrice(ProductPrice price) {
        prices.remove(price);
        price.setProductDetails(null);
    }

}
