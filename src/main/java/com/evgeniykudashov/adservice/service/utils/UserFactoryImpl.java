package com.evgeniykudashov.adservice.service.utils;

import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.mapper.UserMapper;
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

    private final UserMapper userMapper;

    @Override
    public User createUser(UserRequestDto userDto, Supplier<ImageEntity> imageEntitySupplier) {

        User user = userMapper.toUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(Role.USER);

        user.setImageEntity(imageEntitySupplier.get());

        return user;
    }
}
