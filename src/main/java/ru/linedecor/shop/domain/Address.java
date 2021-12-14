package ru.linedecor.shop.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String region;

    private String city;

    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "room_number")
    private String roomNumber;

}
