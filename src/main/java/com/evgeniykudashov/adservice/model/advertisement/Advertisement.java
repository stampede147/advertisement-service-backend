package com.evgeniykudashov.adservice.model.advertisement;

import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.advertisement.valueobject.Description;
import com.evgeniykudashov.adservice.model.advertisement.valueobject.Title;
import com.evgeniykudashov.adservice.model.shared.ActivityDateTime;
import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Advertisement {

    @Id
    private long id;
    private Title title;
    private Description description;
    private Category category;
    private User owner;
    private ActivityDateTime activityDateTime;
    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;
    private Address address;


    public Advertisement() {
    }

    public Advertisement(Title title,
                         Description description,
                         Category category,
                         User owner,
                         Address address,
                         LocalDateTime creationDateTime) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.address = address;
        this.status = AdvertisementStatus.ARCHIVED;
        this.activityDateTime = ActivityDateTime.startFrom(creationDateTime);

    }

    public void changeTitle(Title title, LocalDateTime changeDateTime) {
        this.title = title;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);

    }

    public void changeDescription(Description description, LocalDateTime changeDateTime) {
        this.description = description;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);

    }

    public void changeCategory(Category newCategory, LocalDateTime changeDateTime) {
        this.category = newCategory;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);
    }

    public void changeAddress(Address address, LocalDateTime changeDateTime) {
        this.address = address;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);
    }

    public void makeAdvertisementActive() {
        this.status = AdvertisementStatus.ACTIVE;
    }

    public void makeAdvertisementDeleted() {
        this.status = AdvertisementStatus.DELETED;
    }


}
