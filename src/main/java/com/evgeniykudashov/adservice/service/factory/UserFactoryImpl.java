package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;


@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserFactoryImpl implements UserFactory {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(Supplier<User> userSupplier, String rawPassword, Role role, ImageEntity image) {

        User user = userSupplier.get();

        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setImage(image);

        return user;
    }
}
