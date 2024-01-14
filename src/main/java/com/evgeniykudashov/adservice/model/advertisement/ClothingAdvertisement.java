package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clothing_advertisements")
@DiscriminatorValue(value = AdvertisementType.Constant.CLOTHING_ADVERTISEMENT)
public class ClothingAdvertisement extends CSAAdvertisement {

    @Column(nullable = false)
    private ClothingSize size;


}
