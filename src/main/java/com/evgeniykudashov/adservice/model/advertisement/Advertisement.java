package com.evgeniykudashov.adservice.model.advertisement;


import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(length = 2048, nullable = false)
    private String description;

    private double price;

    @Column(nullable = false)
    private LocalDate startTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private AdvertisementLocation location;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AdvertisementStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seller_id", nullable = false)
    private User seller;

    public Advertisement(Long id,
                         Category category,
                         String title,
                         String description,
                         double price,
                         LocalDate startTime,
                         AdvertisementLocation location,
                         AdvertisementStatus status,
                         User seller) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.startTime = startTime;
        this.location = location;
        this.status = status;
        this.seller = seller;
    }
}
