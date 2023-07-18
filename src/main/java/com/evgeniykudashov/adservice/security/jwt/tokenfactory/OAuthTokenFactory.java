package com.evgeniykudashov.adservice.security.jwt.tokenfactory;

import com.evgeniykudashov.adservice.security.jwt.exception.TokenDecodeException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface OAuthTokenFactory {

    String createToken(String username, Collection<? extends GrantedAuthority> authorities);

    AbstractAuthenticationToken validateAndDecodeToken(String token) throws TokenDecodeException;

}
