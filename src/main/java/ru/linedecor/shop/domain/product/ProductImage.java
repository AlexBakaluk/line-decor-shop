package ru.linedecor.shop.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private byte[] image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImage productImage1 = (ProductImage) o;
        return Arrays.equals(image, productImage1.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }
}
