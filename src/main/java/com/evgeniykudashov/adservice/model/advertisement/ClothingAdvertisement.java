package com.evgeniykudashov.adservice.model.advertisement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

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
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ClothingSize size;

    @Enumerated(value = EnumType.STRING)
    private ClothingType clothingType;

}
