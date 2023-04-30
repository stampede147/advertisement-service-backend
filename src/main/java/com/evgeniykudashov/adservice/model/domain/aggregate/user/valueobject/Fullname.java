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
public class Fullname {

    private String name;
    private String surname;

    public Fullname(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }
}
