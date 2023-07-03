package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.UserResponseDto;

public interface UserService {

    long create(UserRequestDto user);

    void remove(long userId);

    UserResponseDto findById(long userId);

}
