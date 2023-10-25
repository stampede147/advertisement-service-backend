package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.RefreshToken;
import com.evgeniykudashov.adservice.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class RefreshTokenFactoryImpl implements RefreshTokenFactory {

    private final long refreshTokenLifeMillis;

    public RefreshTokenFactoryImpl(@Value("${application.security.jwt.refreshLifeMillis}")
                                   long refreshTokenLifeMillis) {
        this.refreshTokenLifeMillis = refreshTokenLifeMillis;
    }

    @Override
    public RefreshToken create(Supplier<User> userSupplier) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setId(UUID.randomUUID());

        refreshToken.setUser(userSupplier.get());

        LocalDateTime now = LocalDateTime.now();
        refreshToken.setCreatedAt(now);
        refreshToken.setExpiresAt(now.plus(refreshTokenLifeMillis, ChronoUnit.MILLIS));

        return refreshToken;
    }
}
