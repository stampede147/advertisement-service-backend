package com.evgeniykudashov.adservice.model.shared;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Embeddable
@Immutable
public class Title {

    private String data;

    public Title(String data) {
        this.data = data;
    }
}
