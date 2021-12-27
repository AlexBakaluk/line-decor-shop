package ru.linedecor.shop.domain.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Set<ProductCharacteristic> characteristics = new HashSet<>();

//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "productDetails",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ProductImage> additionalImages = new ArrayList<>();

    public void addPrice(ProductPrice price) {
        prices.add(price);
        price.setProductDetails(this);
    }

}
