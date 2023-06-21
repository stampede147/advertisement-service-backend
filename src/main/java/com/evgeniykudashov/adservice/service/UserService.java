package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.shared.security.Username;

import java.util.Map;

public interface UserService {

    long create(User user);

    void patchUpdate(Map<String, Object> map, Long userId);

    void remove(long userId);

    User findById(long userId);

    User findByUsername(Username username);


}
