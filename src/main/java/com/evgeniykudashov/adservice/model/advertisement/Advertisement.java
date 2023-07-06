package com.evgeniykudashov.adservice.model.advertisement;

import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@NoArgsConstructor()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@SuperBuilder

@SecondaryTable(name = "addresses",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "advertisement_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = "advertisement_id"))
@Entity
@Table(name = "advertisements")
@Inheritance(strategy = InheritanceType.JOINED)
@BatchSize(size = 10)
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id")
    @EqualsAndHashCode.Include
    private long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @Immutable
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User owner;

    @Enumerated(value = EnumType.ORDINAL)
    private AdvertisementStatus status;

    @Column(table = "addresses")
    private Address address;

    private long price;

    public Advertisement(long id,
                         @NonNull String title,
                         @NonNull String description,
                         @NonNull User owner,
                         @NonNull AdvertisementStatus status,
                         @NonNull Address address,
                         long price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.status = status;
        this.address = address;
        this.price = price;
    }
}
