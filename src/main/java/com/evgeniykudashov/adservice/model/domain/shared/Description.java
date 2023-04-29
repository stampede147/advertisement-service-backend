package com.evgeniykudashov.adservice.model.domain.shared;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
public class Description {

    private String description;

    public Description(String description) {
        this.description = description;
    }
}
