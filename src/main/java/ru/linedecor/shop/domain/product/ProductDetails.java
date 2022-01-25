package ru.linedecor.shop.domain.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @Column(name = "product_id")
    private Long id;

    @NotEmpty(message = "Bla")
    private String description;

    @MapsId
    @OneToOne
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "measure_id")
    private ProductMeasure measure;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "productDetails",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductPrice> prices = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "products_characteristics",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id")
    )
    private Set<Characteristic> characteristics = new HashSet<>();

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
