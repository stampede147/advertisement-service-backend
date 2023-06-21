package com.evgeniykudashov.adservice.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class BearerAuthenticationConverter implements AuthenticationConverter {

    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";

    private static String extractHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new RuntimeException("not found authorization header");
        }
        return header;
    }

    private static String validate(String header) {
        header = header.trim();
        if (!header.startsWith(AUTHENTICATION_SCHEME_BEARER)) {
            throw new BadCredentialsException("Empty jwt token found");
        }
        return header;
    }

    @Override
    public BearerAuthenticationToken convert(HttpServletRequest request) throws RuntimeException {
        String token = extractBearerToken(request);
        return convert(token);
    }

    private BearerAuthenticationToken convert(String token) {
        return new BearerAuthenticationToken(token);
    }

    private String extractBearerToken(HttpServletRequest request) {
        String header = extractHeader(request);
        validate(header);
        return header.substring(7);
    }

}
