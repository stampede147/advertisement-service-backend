package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Getter
@NoArgsConstructor(onConstructor_ = {@Deprecated})
@EqualsAndHashCode

@Immutable
@Embeddable
public class Address implements Serializable {

    @Column(table = "addresses")
    @JsonProperty(value = "zipCode", required = true)
    private int zipCode;

    @Column(table = "addresses")
    @JsonProperty(value = "city", required = true)
    private String city;

    @Column(table = "addresses")
    @JsonProperty(value = "street", required = true)
    private String street;

    @Column(table = "addresses")
    @JsonProperty(value = "houseNumber", required = true)
    private String houseNumber;

    @JsonCreator()
    public Address(int zipCode,
                   @NonNull String city,
                   @NonNull String street,
                   @NonNull String houseNumber) {
        if (zipCode < 0) {
            throw new IllegalArgumentException("zipcode cannot be negative");
        }
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}
