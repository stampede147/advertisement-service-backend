package com.evgeniykudashov.adservice.model.shared;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Getter
@Immutable
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
