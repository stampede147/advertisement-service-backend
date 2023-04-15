package com.evgeniykudashov.adservice.model.Advertisement;

import com.evgeniykudashov.adservice.model.Advertisement.valueobject.Address;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

public class Advertisement {
    @Id
    private long advertisementId;
    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String description;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private User owner;

    @Temporal(value = TemporalType.DATE)
    private LocalDate createdDate;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
