package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clothing_sizes")
public class ClothingSize {

    @Id
    private int size;

    @Column(unique = true)
    private String description;

//    XS(42, "XS"), XS_S(44, "XS/S"), S(46, "S"), M(48, "M");

}
