package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.RefreshToken;
import com.evgeniykudashov.adservice.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class RefreshTokenFactoryImpl implements RefreshTokenFactory {

    private static final long REFRESH_TOKEN_LIFE_MILLIS = 7 * 24 * 60 * 1000; // 7 days

    @Override
    public RefreshToken create(Supplier<User> userSupplier) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setId(UUID.randomUUID());

        refreshToken.setUser(userSupplier.get());

        LocalDateTime now = LocalDateTime.now();
        refreshToken.setCreatedAt(now);
        refreshToken.setExpiresAt(now.plus(REFRESH_TOKEN_LIFE_MILLIS, ChronoUnit.MILLIS));

        return refreshToken;
    }
}
