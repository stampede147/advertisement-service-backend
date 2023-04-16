package com.evgeniykudashov.adservice.model.advertisement.valueobject;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Embeddable
@Immutable
public class Title {

    private String data;

    @Deprecated
    protected Title() {
    }

    public Title(String data) {
        this.data = data;
    }
}
