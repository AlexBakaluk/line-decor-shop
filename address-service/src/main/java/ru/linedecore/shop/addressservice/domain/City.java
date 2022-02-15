package ru.linedecore.shop.addressservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.linedecore.shop.addressservice.dto.request.CityDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    @JsonIgnore
    @OneToMany(
            mappedBy = "city",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Street> streets = new ArrayList<>();

    public City(CityDto dto) {
        this.name = dto.getName();
    }

    public void addStreet(Street street) {
        streets.add(street);
        street.setCity(this);
    }

    public void removeStreet(Street street) {
        streets.remove(street);
        street.setCity(null);
    }

}
