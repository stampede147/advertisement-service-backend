package com.evgeniykudashov.adservice.model.advertisementEdit;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ViewConfig {

    @Enumerated(value = EnumType.STRING)
    private ViewSize size;

    @Enumerated(value = EnumType.STRING)
    private ViewType type;
}
