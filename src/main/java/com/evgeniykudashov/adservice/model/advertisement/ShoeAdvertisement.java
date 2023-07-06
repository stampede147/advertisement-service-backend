package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "footwear_advertisements")
public class ShoeAdvertisement extends Advertisement {

    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size")
    private ShoeSize size;


}
