package com.evgeniykudashov.adservice.configuration;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;

import java.security.Principal;

@Configuration
public class PrincipalConverterConfiguration {

    private static class PrincipalConverter implements Converter<Principal, Long> {
        @Override
        public Long convert(Principal source) {
            return Long.valueOf(source.getName());
        }
    }

    @Bean
    @Scope
    public Converter<Principal, Long> principalConverter() {
        return new PrincipalConverter();
    }


}
