package com.evgeniykudashov.adservice.model.domain.advertisement.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
public class Description {

    @Getter
    private String data;

    public Description(String data) {
        this.data = data;
    }
}
