package com.evgeniykudashov.adservice.model.advertisement.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Immutable
@Getter
@Embeddable
public class Address implements Serializable {
    private int zipCode;
    private String city;
    private String street;
    private String houseNumber;

    public Address() {
    }

    public Address(int zipCode, String city, String street, String houseNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
