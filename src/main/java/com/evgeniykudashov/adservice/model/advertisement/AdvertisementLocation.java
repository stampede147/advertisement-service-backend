package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advertisement_location")
@BatchSize(size = 10)
public class AdvertisementLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private long id;

    @NonNull
    private String city;

    @NonNull
    private String street;
}
