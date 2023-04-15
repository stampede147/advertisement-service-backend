package com.evgeniykudashov.adservice.model.user.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Immutable
@Getter
@Embeddable
public class OnlineTime implements Serializable {

    private LocalDateTime dateTime;


    @Deprecated
    public OnlineTime() {
    }

    public OnlineTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
