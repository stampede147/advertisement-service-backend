package com.evgeniykudashov.adservice.model.advertisement;


import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private Long id;

    private String title;

    @Column(length = 512)
    private String description;

    private double price;

    private LocalDate startTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private AdvertisementLocation location;

    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seller_id")
    private User seller;

}
