package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.user.User;

import java.util.function.Supplier;

public interface UserFactory {


    User createUser(Supplier<User> userSupplier);
}
