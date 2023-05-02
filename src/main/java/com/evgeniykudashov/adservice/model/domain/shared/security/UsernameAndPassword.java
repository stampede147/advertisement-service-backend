package com.evgeniykudashov.adservice.model.domain.shared.security;


import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Embeddable
@Immutable
public class UsernameAndPassword {
    private String username;
    private String password;

    public UsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
