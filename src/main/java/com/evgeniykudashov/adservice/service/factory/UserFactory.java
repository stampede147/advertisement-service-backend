package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.model.user.User;

import java.util.function.Supplier;

public interface UserFactory {

    User createUser(Supplier<User> userSupplier, String rawPassword, Role role, ImageEntity image);

    default User createUser(String rawPassword) {
        return createUser(User::new, rawPassword, Role.USER, null);
    }
}
