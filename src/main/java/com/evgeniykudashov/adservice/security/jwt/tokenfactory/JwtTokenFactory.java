package com.evgeniykudashov.adservice.security.jwt.tokenfactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.security.jwt.OAuthAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Component("jwtTokenFactory")
public class JwtTokenFactory implements OAuthTokenFactory {

    protected String jwtSecret;

    protected int tokenLifeMillis;

    private final Algorithm algorithm;

    public JwtTokenFactory(@Value("${application.security.jwt.secret}") String jwtSecret,
                           @Value("${application.security.jwt.lifeMillis}") int tokenLifeMillis) {
        this.jwtSecret = jwtSecret;
        this.tokenLifeMillis = tokenLifeMillis;
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    }

    @Override
    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return JWT.create()
                .withClaim(JwtTokenConstants.sub, username)
                .withArrayClaim(JwtTokenConstants.roles, authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new))
                .withClaim(JwtTokenConstants.issuedAt, Instant.now())
                .withClaim(JwtTokenConstants.expiresAt, Instant.now().plusMillis(tokenLifeMillis))
                .sign(algorithm);
    }

    @Override
    public JwtAuthenticationToken validateAndDecodeToken(String token) {
        DecodedJWT rawToken = validate(token);
        return new JwtAuthenticationToken(
                List.of(rawToken.getClaim(JwtTokenConstants.roles).asArray(Role.class)),
                rawToken.getSubject(),
                rawToken.getExpiresAtAsInstant(),
                rawToken.getIssuedAtAsInstant());
    }

    private DecodedJWT validate(String token) {
        return JWT.require(algorithm).build().
                verify(token);
    }

    protected static class JwtAuthenticationToken extends OAuthAuthenticationToken {

        public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                      String username,
                                      Instant expiresAt,
                                      Instant issuedAt) {
            super(authorities, username, expiresAt, issuedAt);
        }

    }
}
