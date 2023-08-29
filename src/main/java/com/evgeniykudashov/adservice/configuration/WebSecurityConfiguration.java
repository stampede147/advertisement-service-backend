package com.evgeniykudashov.adservice.configuration;


import com.evgeniykudashov.adservice.security.jwt.BearerAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor(onConstructor_ = @Autowired)
@ConditionalOnBean(SecurityComponentConfiguration.class)
public class WebSecurityConfiguration {

    protected BearerAuthenticationFilter filter;


    @Bean()
    @SneakyThrows
    protected SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .securityContext().and()
                .authorizeHttpRequests(r -> {
                    r.requestMatchers("/api/v1/advertisements/**").authenticated();
                    r.requestMatchers("/api/v1/users/**").authenticated();
                    r.requestMatchers("/api/v1/chats/**").authenticated();
                    r.requestMatchers("/api/v1/chat-messages/**").authenticated();
                    r.requestMatchers("/api/v1/authentications/**").anonymous();
                    r.anyRequest().permitAll();
                    })
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
                    config.addAllowedOrigin("http://localhost:3000");
                    config.addExposedHeader(HttpHeaders.COOKIE);
                    config.addAllowedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS);
                    config.addAllowedHeader(HttpHeaders.CONTENT_TYPE);
                    config.addAllowedHeader(HttpHeaders.AUTHORIZATION);
//                    config.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION, HttpHeaders.LOCATION));
                    config.addAllowedHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS);
                    config.addAllowedMethod("*");
                    source.registerCorsConfiguration("/**", config);

                    cors.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


}
