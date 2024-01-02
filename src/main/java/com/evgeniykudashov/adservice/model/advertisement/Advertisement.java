package com.evgeniykudashov.adservice.model.advertisement;


import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advertisements", indexes = @Index(columnList = "title"))
@BatchSize(size = 10)
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

    @ManyToMany()
    @JoinTable(name = "advertisements_images",
            joinColumns = @JoinColumn(name = "advertisement_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<ImageEntity> images;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertisement that)) return false;
        return Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getStartTime(), that.getStartTime()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getStartTime(), getStatus());
    }
}
