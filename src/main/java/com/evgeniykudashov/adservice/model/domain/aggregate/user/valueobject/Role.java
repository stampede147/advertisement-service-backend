package com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {

    USER(Constants.USER),
    ADMIN(Constants.ADMIN);

    String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getAuthority() {
        return this.getValue();
    }

    public static class Constants {
        public static final String ROLE_PREFIX = "ROLE_";
        public static final String ADMIN = ROLE_PREFIX.concat("ADMIN");
        public static final String USER = ROLE_PREFIX.concat("USER");
    }
}
