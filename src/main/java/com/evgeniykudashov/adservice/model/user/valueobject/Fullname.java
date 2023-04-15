package com.evgeniykudashov.adservice.model.user.valueobject;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Immutable
@Getter
@Embeddable
public class Fullname {


    private String name;
    private String surname;

    @Deprecated
    public Fullname() {
    }

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
