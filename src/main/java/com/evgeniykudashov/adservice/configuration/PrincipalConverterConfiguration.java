package com.evgeniykudashov.adservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.security.Principal;

@Configuration
public class PrincipalConverterConfiguration {

    @Bean
    public Converter<Principal, Long> principalConverter() {
        return new PrincipalToUserIdConverter();
    }

    private static class PrincipalToUserIdConverter implements Converter<Principal, Long> {
        @Override
        public Long convert(Principal source) {
            return Long.valueOf(source.getName());
        }
    }


}
