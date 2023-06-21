package com.evgeniykudashov.adservice.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;

public class JwtAuthenticationToken extends OAuthAuthenticationToken {

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                  String username,
                                  Instant expiresAt,
                                  Instant issuedAt) {
        super(authorities, username, expiresAt, issuedAt);
    }


}
