package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Immutable
@Embeddable
public class Address implements Serializable {

    @Column(table = "addresses")
    private int zipCode;

    @Column(table = "addresses")
    private String city;

    @Column(table = "addresses")
    private String street;

    @Column(table = "addresses")
    private String houseNumber;

    public Address(int zipCode, String city, String street, String houseNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
