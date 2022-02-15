package ru.linedecore.shop.addressservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 3)
    private String code;

    @Column(unique = true)
    private String fullName;

    @NaturalId
    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    @NaturalId
    @Column(nullable = false, unique = true, length = 2)
    private String alpha2;

    @Column(nullable = false, unique = true, length = 3)
    private String alpha3;

    @JsonIgnore
    @OneToMany(
            mappedBy = "country",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Region> regions = new ArrayList<>();

    public void addRegion(Region region) {
        regions.add(region);
        region.setCountry(this);
    }

    public void removeRegion(Region region) {
        regions.remove(region);
        region.setCountry(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return shortName.equals(country.shortName);
    }

    @Override
    public int hashCode() {
        return shortName.hashCode();
    }
}
