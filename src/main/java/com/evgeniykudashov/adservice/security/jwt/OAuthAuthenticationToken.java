package com.evgeniykudashov.adservice.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;

public class OAuthAuthenticationToken extends AbstractAuthenticationToken {

    protected String username;

    protected Instant expiresAt;

    protected Instant issuedAt;

    public OAuthAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                    String username,
                                    Instant expiresAt,
                                    Instant issuedAt) {
        super(authorities);
        this.username = username;
        this.expiresAt = expiresAt;
        this.issuedAt = issuedAt;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }
}
