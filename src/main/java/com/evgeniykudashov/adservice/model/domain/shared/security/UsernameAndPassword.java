package com.evgeniykudashov.adservice.model.domain.shared.security;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
@EqualsAndHashCode

@Embeddable
@Immutable
public class UsernameAndPassword {
    private String username;
    private String password;
}
