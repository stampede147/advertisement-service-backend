package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundUserException;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public long create(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void updatePersonalDetails(PersonalDetails personalDetails, long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        user.updatePersonalDetails(personalDetails);
        userRepository.save(user);
    }

    @Override
    public void updateAccessDetails(AccessDetails accessDetails, long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        user.updateAccessDetails(accessDetails);
        userRepository.save(user);
    }

    @Override
    public void remove(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
    }
}
