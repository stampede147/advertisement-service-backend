package com.evgeniykudashov.adservice.model.domain.shared;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Embeddable
@Immutable
public class Title {

    private String title;

    public Title(String title) {
        this.title = title;
    }
}
