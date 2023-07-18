package com.evgeniykudashov.adservice.security.jwt.tokenfactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.security.jwt.JwtAuthenticationToken;
import com.evgeniykudashov.adservice.security.jwt.exception.TokenDecodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Component("jwtTokenFactory")
@Slf4j
public class JwtTokenFactory implements OAuthTokenFactory {

    protected String jwtSecret;

    protected int tokenLifeMillis;

    private final Algorithm algorithm;

    private final JWTVerifier tokenVerifier;

    public JwtTokenFactory(@Value("${application.security.jwt.secret}") String jwtSecret,
                           @Value("${application.security.jwt.lifeMillis}") int tokenLifeMillis) {
        this.jwtSecret = jwtSecret;
        this.tokenLifeMillis = tokenLifeMillis;
        this.algorithm = buildAlgorithm(jwtSecret);
        this.tokenVerifier = buildTokenVerifier(algorithm);

    }

    private static Algorithm buildAlgorithm(String jwtSecret) {
        return Algorithm.HMAC256(jwtSecret.getBytes());
    }

    private static JWTVerifier buildTokenVerifier(Algorithm alg) {
        return JWT.require(alg).build();
    }

    @Override
    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        log.trace("Started createToken(String, Collection> method");
        log.debug("Provided parameters username: {}, authorities: {}", username, authorities);

        return JWT.create()
                .withClaim(JwtTokenConstants.SUB, username)
                .withArrayClaim(JwtTokenConstants.ROLES, authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new))
                .withClaim(JwtTokenConstants.ISSUED_AT, Instant.now())
                .withClaim(JwtTokenConstants.EXPIRES_AT, Instant.now().plusMillis(tokenLifeMillis))
                .sign(algorithm);
    }

    @Override
    public JwtAuthenticationToken validateAndDecodeToken(String token) {
        log.trace("Started validateAndDecodeToken(String) method");

        DecodedJWT decoded = decodeToken(token);

        return new JwtAuthenticationToken(List.of(decoded.getClaim(JwtTokenConstants.ROLES)
                .asArray(Role.class)),
                decoded.getClaim(JwtTokenConstants.SUB)
                        .asString());
    }

    private DecodedJWT decodeToken(String token) {
        log.trace("Started decoding token");

        DecodedJWT decoded = decodeInternal(token);

        log.trace("Token successfully decoded");

        return decoded;
    }

    private DecodedJWT decodeInternal(String token) {
        DecodedJWT decoded;
        try {
            decoded = tokenVerifier.verify(token);
        } catch (JWTVerificationException e) {
            TokenDecodeException tokenDecodeException = new TokenDecodeException(e);
            log.error("Unexpected token exception", tokenDecodeException);
            throw tokenDecodeException;
        }
        return decoded;
    }

}
