package com.evgeniykudashov.adservice.security.jwt;

import com.evgeniykudashov.adservice.security.jwt.exception.DecodeException;
import com.evgeniykudashov.adservice.security.jwt.tokenfactory.OAuthTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("bearerAuthenticationProvider")
public class JwtAuthenticationProvider implements AuthenticationProvider {


    public static final String JWT_TOKEN_FACTORY = "jwtTokenFactory";
    private final OAuthTokenFactory factory;

    @Autowired
    public JwtAuthenticationProvider(@Qualifier(JWT_TOKEN_FACTORY) OAuthTokenFactory factory) {
        this.factory = factory;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerAuthenticationToken token = (BearerAuthenticationToken) authentication;
        AbstractAuthenticationToken abstractAuthenticationToken;
        try {
            abstractAuthenticationToken = factory.validateAndDecodeToken(token.getToken());
        } catch (DecodeException e) {
            throw new InternalAuthenticationServiceException("problems with decoding jwt token", e);
        }
        abstractAuthenticationToken.setAuthenticated(true);
        return abstractAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
