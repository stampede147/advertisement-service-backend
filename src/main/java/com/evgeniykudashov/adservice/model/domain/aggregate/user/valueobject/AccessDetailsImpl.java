package com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject;

import com.evgeniykudashov.adservice.model.domain.shared.security.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.shared.security.UsernameAndPassword;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
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
public class AccessDetailsImpl implements AccessDetails {


    private UsernameAndPassword usernameAndPassword;

    public AccessDetailsImpl(UsernameAndPassword usernameAndPassword) {
        this.usernameAndPassword = usernameAndPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return usernameAndPassword.getPassword();
    }

    @Override
    public String getUsername() {
        return usernameAndPassword.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
