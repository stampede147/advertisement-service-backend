package com.evgeniykudashov.adservice.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class BearerAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public BearerAuthenticationToken(String token) {
        super(null);
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
