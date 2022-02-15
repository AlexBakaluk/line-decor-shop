package ru.linedecore.shop.addressservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.linedecore.shop.addressservice.dto.request.StreetDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "street")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne
    private City city;

    @JsonIgnore
    @OneToMany(
            mappedBy = "street",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<House> houses = new ArrayList<>();

    public Street(StreetDto dto) {
        this.name = dto.getName();
    }

    public void addHouse(House house) {
        houses.add(house);
        house.setStreet(this);
    }

    public void removeHouse(House house) {
        houses.remove(house);
        house.setStreet(null);
    }

}
