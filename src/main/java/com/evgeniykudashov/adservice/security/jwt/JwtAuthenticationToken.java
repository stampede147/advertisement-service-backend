package com.evgeniykudashov.adservice.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String userId;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                  String userId) {
        super(authorities);
        this.userId = userId;
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
