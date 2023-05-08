package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;

public interface UserService {

    long create(User user);

    void updatePersonalDetails(PersonalDetails personalDetails, long userId);

    void updateAccessDetails(AccessDetails accessDetails, long userId);

    void remove(long userId);

    User findById(Long userId);

}
