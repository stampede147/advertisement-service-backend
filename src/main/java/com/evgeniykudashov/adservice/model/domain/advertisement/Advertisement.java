package com.evgeniykudashov.adservice.model.domain.advertisement;

import com.evgeniykudashov.adservice.model.domain.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.advertisement.valueobject.Description;
import com.evgeniykudashov.adservice.model.domain.advertisement.valueobject.Title;
import com.evgeniykudashov.adservice.model.domain.category.Category;
import com.evgeniykudashov.adservice.model.domain.user.User;
import jakarta.persistence.*;

public class Advertisement {

    @Id
    @Column(name = "advertisement_id")
    private long id;
    private Title title;
    private Description description;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private User owner;
    @Embedded
    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;
    @Embedded
    private Address address;


    public Advertisement() {
    }

    public Advertisement(Title title,
                         Description description,
                         Category category,
                         User owner,
                         Address address) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.address = address;
        this.status = AdvertisementStatus.ARCHIVED;

    }

    public void changeTitle(Title title) {
        this.title = title;

    }

    public void changeDescription(Description description) {
        this.description = description;

    }

    public void changeCategory(Category newCategory) {
        this.category = newCategory;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void makeAdvertisementActive() {
        this.status = AdvertisementStatus.ACTIVE;
    }

    public void makeAdvertisementDeleted() {
        this.status = AdvertisementStatus.DELETED;
    }


}
