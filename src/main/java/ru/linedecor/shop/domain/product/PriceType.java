package ru.linedecor.shop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price_type")
public class PriceType {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "price_type_sequence"
    )
    @SequenceGenerator(
            name = "price_type_sequence",
            sequenceName = "price_type_sequence",
            allocationSize = 3
    )
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceType priceType = (PriceType) o;
        return name.equals(priceType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
