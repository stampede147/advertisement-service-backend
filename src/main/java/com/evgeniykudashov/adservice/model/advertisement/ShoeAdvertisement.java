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
@Table(name = "footwear_advertisements")
@DiscriminatorValue(value = AdvertisementType.Constants.SHOE)
public class ShoeAdvertisement extends Advertisement {

    private String brand;

    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color")
    private ThingColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size")
    private ShoeSize size;


}
