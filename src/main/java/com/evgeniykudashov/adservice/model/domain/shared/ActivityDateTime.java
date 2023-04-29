package com.evgeniykudashov.adservice.model.domain.shared;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Getter
@Immutable
@Embeddable
public final class ActivityDateTime {

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime firstUpdateDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdateDateTime;

    @Deprecated
    public ActivityDateTime() {
    }

    public ActivityDateTime(LocalDateTime firstUpdateDateTime,
                            LocalDateTime lastUpdateDateTime) {
        if (firstUpdateDateTime == null) {
            throw new RuntimeException("firstUpdateDateTime can not be null");
        }
        if (lastUpdateDateTime == null) {
            throw new RuntimeException("lastUpdateDateTime can not be null");
        }
        if (firstUpdateDateTime.compareTo(lastUpdateDateTime) < 0) {
            throw new RuntimeException(("lastUpdateDatetime can not be earlier than firstUpdateDateTime"));
        }
        this.firstUpdateDateTime = firstUpdateDateTime;
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public static ActivityDateTime startFrom(LocalDateTime startDateTime) {
        return new ActivityDateTime(startDateTime, startDateTime);
    }

    public ActivityDateTime withLastUpdateDateTime(LocalDateTime dateTime) {
        return new ActivityDateTime(this.firstUpdateDateTime, dateTime);
    }
}
