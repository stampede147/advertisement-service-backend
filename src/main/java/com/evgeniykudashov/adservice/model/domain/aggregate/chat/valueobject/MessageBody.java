package com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
@Access(AccessType.FIELD)
public class MessageBody implements Serializable {

    private String body;

    public MessageBody(String body) {
        this.body = body;
    }
}
