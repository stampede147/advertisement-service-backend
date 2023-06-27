package com.evgeniykudashov.adservice.model.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER(Roles.ROLE_USER), ADMIN(Roles.ROLE_ADMIN);

    String authority;

    Role(String val) {
        this.authority = val;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public static class Roles {
        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
        protected static final String ROLE_PREFIX = "ROLE_";
        public static final String ROLE_USER = ROLE_PREFIX + USER;
        public static final String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    }
}
