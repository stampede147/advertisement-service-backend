package com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject;


import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Immutable
@Embeddable
public class PersonalDetails {

    private Fullname fullname;

    public PersonalDetails(Fullname fullname) {
        this.fullname = fullname;
    }
}
