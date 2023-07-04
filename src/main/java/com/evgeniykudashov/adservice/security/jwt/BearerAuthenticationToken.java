package com.evgeniykudashov.adservice.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class BearerAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public BearerAuthenticationToken(String token) {
        super(List.of());
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
