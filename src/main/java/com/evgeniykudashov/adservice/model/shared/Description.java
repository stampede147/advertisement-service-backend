package com.evgeniykudashov.adservice.model.shared;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
public class Description {

    private String data;

    public Description(String data) {
        this.data = data;
    }
}
