package com.evgeniykudashov.adservice.model.advertisement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "clothing_advertisements")
public class ClothingAdvertisement extends Advertisement {


    private String brand;

    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color")
    private ThingColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size")
    private ClothingSize size;

    @Enumerated(value = EnumType.STRING)
    private ClothingType clothingType;

}
