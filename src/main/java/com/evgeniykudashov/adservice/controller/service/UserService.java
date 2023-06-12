package com.evgeniykudashov.adservice.controller.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;

import java.util.Map;

public interface UserService {

    long create(User user);

    void patchUpdate(Map<String, Object> map, Long userId);

    void remove(long userId);

    User findById(long userId);


}
