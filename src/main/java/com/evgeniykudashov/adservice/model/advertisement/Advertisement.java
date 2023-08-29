package com.evgeniykudashov.adservice.model.advertisement;

import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.time.LocalDate;

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
@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name = "type")
@BatchSize(size = 10)
public class Advertisement implements Serializable {

    public static final String DISCRIMINATOR_COLUMN = "type";
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
    private User owner;

    @Enumerated(value = EnumType.STRING)
    private AdvertisementStatus status;

    @Column(table = "addresses")
    private Address address;

    private long price;

    @Enumerated(EnumType.STRING)
    @Column(name = DISCRIMINATOR_COLUMN, updatable = false, insertable = false)
    private AdvertisementType type;

    private LocalDate createdAt;

    public Advertisement(long id,
                         @NonNull String title,
                         @NonNull String description,
                         @NonNull User owner,
                         @NonNull AdvertisementStatus status,
                         @NonNull Address address,
                         long price,
                         @NotNull AdvertisementType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.status = status;
        this.address = address;
        this.price = price;
        this.type = type;
    }
}
