package com.evgeniykudashov.adservice.model.advertisement.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Immutable
@Embeddable
public class Description {

    @Getter
    private String data;

    @Deprecated
    protected Description() {
    }

    public Description(String data) {
        this.data = data;
    }
}
