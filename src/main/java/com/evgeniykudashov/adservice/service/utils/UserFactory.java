package com.evgeniykudashov.adservice.service.utils;

import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;

import java.util.function.Supplier;

public interface UserFactory {


    User createUser(UserRequestDto userDto, Supplier<ImageEntity> imageEntitySupplier);
}
