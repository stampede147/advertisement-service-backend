package com.evgeniykudashov.adservice.model.domain.shared.security;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails extends CredentialsContainer {

    Collection<? extends GrantedAuthority> getAuthorities();

    Password getPassword();

    Username getUsername();

    boolean isEnabled();
}
