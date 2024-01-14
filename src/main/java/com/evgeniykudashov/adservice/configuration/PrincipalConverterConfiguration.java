package com.evgeniykudashov.adservice.configuration;

import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

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

    @Component
    private static final class PrincipalToUserConverter implements Converter<Principal, User> {

        private final UserRepository userRepository;

        private final Converter<Principal, Long> converter;

        @Autowired
        public PrincipalToUserConverter(UserRepository userRepository, Converter<Principal, Long> converter) {
            this.userRepository = userRepository;
            this.converter = converter;
        }

        @Override
        public User convert(Principal source) {

            Long convert = converter.convert(source);
            return userRepository.getReferenceById(convert);
        }
    }

}
