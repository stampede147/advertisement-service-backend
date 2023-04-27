package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    private long id;
    private Title title;
    private Description description;
    @OneToOne
    @JoinColumn(name = "category_id")
    @Immutable
    private Category category;
    @OneToOne
    @JoinColumn(name = "user_owner_id")
    @Immutable
    private User owner;
    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;
    private Address address;

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

    public void makeActive() {
        this.status = AdvertisementStatus.ACTIVE;
    }

    public void makeDeleted() {
        this.status = AdvertisementStatus.DELETED;
    }


}
