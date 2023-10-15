package com.evgeniykudashov.adservice.model.user;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER(Constants.ROLE_USER), ADMIN(Constants.ROLE_ADMIN);

    @JsonValue
    String authority;

    Role(String val) {
        this.authority = val;
    }

    @Override
    public String getAuthority() {
        return authority;
    }



    public static class Constants {
        private static final String USER = "USER";
        private static final String ADMIN = "ADMIN";
        private static final String ROLE_PREFIX = "ROLE_";
        public static final String ROLE_USER = ROLE_PREFIX + USER;
        public static final String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    }

}
