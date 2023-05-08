package com.evgeniykudashov.adservice.model.domain.shared.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface CustomUserDetails {

    Collection<? extends GrantedAuthority> getAuthorities();

    Password getPassword();

    Username getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
