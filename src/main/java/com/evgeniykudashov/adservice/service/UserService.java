package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.request.UserCreateRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.UserResponseDto;

public interface UserService {

    long create(UserCreateRequestDto user);

    void remove(long userId);

    UserResponseDto findById(long userId);

}
