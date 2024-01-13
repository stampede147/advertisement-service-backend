package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "csa_advertisements")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CSAAdvertisement extends Advertisement {

    private String healthCondition;

    private String brand;

    private String color;

}
