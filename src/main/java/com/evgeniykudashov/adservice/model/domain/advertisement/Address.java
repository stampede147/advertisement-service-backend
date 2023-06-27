package com.evgeniykudashov.adservice.model.domain.advertisement;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    int zipcode;

    String city;

    String street;

    String houseNumber;

}
