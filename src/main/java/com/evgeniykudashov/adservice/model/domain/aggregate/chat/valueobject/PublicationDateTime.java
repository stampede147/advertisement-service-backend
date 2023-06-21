package com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject;


import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
@Access(AccessType.FIELD)
public class PublicationDateTime implements Serializable {

    private LocalDateTime publicationDateTime;

    @JsonCreator
    public PublicationDateTime(LocalDateTime publicationDateTime) {
        this.publicationDateTime = publicationDateTime;
    }
}
