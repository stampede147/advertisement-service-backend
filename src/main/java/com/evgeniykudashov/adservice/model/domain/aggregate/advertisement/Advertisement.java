package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement;

import com.evgeniykudashov.adservice.annotation.Default;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@NoArgsConstructor(onConstructor_ = @Deprecated)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SecondaryTable(name = "addresses",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "advertisement_id"),
        indexes = @Index(columnList = "zipCode, city, street, houseNumber"))
@Entity
@Table(name = "advertisements")
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    @EqualsAndHashCode.Include
    @Getter
    private Long id;

    @Getter
    private Title title;

    @Getter
    private Description description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false, nullable = false)
    @Immutable
    @Getter
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", updatable = false, nullable = false)
    @Immutable
    @Getter
    private User owner;

    @Enumerated(value = EnumType.ORDINAL)
    @Getter
    private AdvertisementStatus status;

    @Column(table = "addresses")
    @Getter
    private Address address;


    @Default
    public Advertisement(Title title,
                         Description description,
                         Category category,
                         User owner,
                         Address address) {
        this(0L, title, description, category, owner, address, AdvertisementStatus.ARCHIVED);
    }

    @JsonCreator
    public Advertisement(@NonNull Long id,
                         @NonNull Title title,
                         @NonNull Description description,
                         @NonNull Category category,
                         @NonNull User owner,
                         @NonNull Address address,
                         @NonNull AdvertisementStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.address = address;
        this.status = status;
    }

    public void updateTitle(Title title) {
        this.title = title;
    }

    public void updateDescription(Description description) {
        this.description = description;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updateStatus(AdvertisementStatus status) {
        this.status = status;
    }
}
