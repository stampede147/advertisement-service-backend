package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.RefreshToken;
import com.evgeniykudashov.adservice.model.user.User;

import java.util.function.Supplier;

public interface RefreshTokenFactory {

    RefreshToken create(Supplier<User> userSupplier);
}
