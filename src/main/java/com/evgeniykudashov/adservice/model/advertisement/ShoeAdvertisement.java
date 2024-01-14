package com.evgeniykudashov.adservice.model.advertisement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shoe_advertisements")
@DiscriminatorValue(value = AdvertisementType.Constant.SHOE_ADVERTISEMENT)
public class ShoeAdvertisement extends CSAAdvertisement {

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ShoeSize size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoeAdvertisement that)) return false;
        if (!super.equals(o)) return false;
        return getSize() == that.getSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSize());
    }
}
