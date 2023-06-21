package com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject;


import com.evgeniykudashov.adservice.model.domain.shared.security.Password;
import com.evgeniykudashov.adservice.model.domain.shared.security.UserDetails;
import com.evgeniykudashov.adservice.model.domain.shared.security.Username;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Immutable
@Embeddable
@Access(AccessType.FIELD)
public class AccessDetails implements UserDetails {

    private Username username;
    private Password password;

    @ElementCollection()
    @CollectionTable(name = "roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Collection<Role> authorities;

    public AccessDetails(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
