package com.evgeniykudashov.adservice.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Setter
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private boolean isPostSupported;

    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private BearerAuthenticationConverter converter = new BearerAuthenticationConverter();
    private AuthenticationManager authenticationManager;


    public JwtAuthenticationFilter() {
        this.isPostSupported = true;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            BearerAuthenticationToken bearerAuthentication = converter.convert(request);

            Authentication authentication = authenticationManager.authenticate(bearerAuthentication);
//            validateAuthentication(authentication);

            onSuccessfulAuthentication(request, response, filterChain, authentication);
        } catch (AuthenticationException e) {
            onUnsuccessfulAuthentication(request, response, filterChain);
        }

        filterChain.doFilter(request, response);
    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        this.securityContextHolderStrategy.clearContext();

    }

    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
    }

    protected void validateAuthentication(Authentication other) {
        Authentication authentication = securityContextHolderStrategy.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.equals(other)) {
            throw new BadCredentialsException("authentication in context does not equal with provided authentication");
        }

    }

    protected boolean requiresAuthentication(HttpServletRequest request) {
        return containsHeader(request);
    }

    private boolean containsHeader(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
    }

    private boolean hasRequestMethod(HttpServletRequest request) {
        return isPostSupported && "POST".equalsIgnoreCase(request.getMethod());
    }

}
