package ru.linedecor.shop.domain.product.characteristic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.linedecor.shop.domain.product.ProductDetails;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products_characteristics")
public class ProductsCharacteristics {

    @EmbeddedId
    private ProductCharacteristicId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductDetails details;

    @MapsId("characteristicId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Characteristic characteristic;

    @Column(name = "characteristic_value", nullable = false, updatable = false, length = 510)
    private String characteristicValue;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.details);
        hash = 29 * hash + Objects.hashCode(this.characteristic);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductsCharacteristics other = (ProductsCharacteristics) obj;
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.characteristic, other.characteristic)) {
            return false;
        }
        return true;
    }
}
