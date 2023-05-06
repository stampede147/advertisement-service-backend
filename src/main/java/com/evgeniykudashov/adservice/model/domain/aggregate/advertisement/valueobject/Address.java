package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode
@Getter

@Immutable
@Entity
@Table(name = "addresses", indexes = @Index(columnList = "zipCode, city, street, houseNumber"))
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @EqualsAndHashCode.Exclude
    private long id;

    private int zipCode;

    private String city;

    private String street;

    private String houseNumber;

    public Address(int zipCode, String city, String street, String houseNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
