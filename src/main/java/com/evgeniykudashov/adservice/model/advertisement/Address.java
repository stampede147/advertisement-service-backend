package com.evgeniykudashov.adservice.model.advertisement;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    @Column(table = "addresses")
    Integer zipcode;

    @Column(table = "addresses")
    String city;

    @Column(table = "addresses")
    String street;

    @Column(table = "addresses")
    String houseNumber;

}
