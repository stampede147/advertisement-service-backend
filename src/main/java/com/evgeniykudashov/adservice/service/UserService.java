package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.view.dto.UserDto;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void remove(UserDto userDto);

    UserDto findById(Long id);

}
