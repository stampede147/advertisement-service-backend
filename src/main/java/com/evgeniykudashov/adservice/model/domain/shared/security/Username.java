package com.evgeniykudashov.adservice.model.domain.shared.security;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Immutable
@Embeddable
public class Username {
    String username;
}
