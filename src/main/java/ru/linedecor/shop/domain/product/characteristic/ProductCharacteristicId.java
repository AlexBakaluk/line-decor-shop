package ru.linedecor.shop.domain.product.characteristic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProductCharacteristicId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "details_product_id")
    private Long productId;

    @Column(name = "characteristic_id")
    private Long characteristicId;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.productId);
        hash = 31 * hash + Objects.hashCode(this.characteristicId);
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
        final ProductCharacteristicId other = (ProductCharacteristicId) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        if (!Objects.equals(this.characteristicId, other.characteristicId)) {
            return false;
        }
        return true;
    }
}
