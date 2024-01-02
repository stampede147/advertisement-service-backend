package com.evgeniykudashov.adservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.security.Principal;
import java.util.regex.Pattern;

@Configuration
public class PrincipalConverterConfiguration {

    @Bean
    public Converter<Principal, Long> principalConverter() {
        return new PrincipalToUserIdConverter();
    }

    private static class PrincipalToUserIdConverter implements Converter<Principal, Long> {

        private final static Pattern numberPattern = Pattern.compile("\\b\\d+\\b");

        @Override
        public Long convert(Principal source) {
            String name = source.getName();
            return numberPattern.matcher(name).find()  // checks name is number
                    ? Long.valueOf(name)
                    : null;
        }
    }
}
