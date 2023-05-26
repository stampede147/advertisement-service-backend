package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl sut;

    @Test
    void create() {
        User user = TestValues.getUserObject();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        sut.create(user);

        Mockito.verify(userRepository).save(Mockito.any(user.getClass()));
    }

    @Test
    void updatePersonalDetails() {
        User user = TestValues.getUserObject();
        PersonalDetails personalDetails = TestValues.getPersonalDetailsObject();
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));


        sut.updatePersonalDetails(personalDetails, user.getId());

        Mockito.verify(userRepository).save(user);
    }

    @Test
    void updateAccessDetails() {
        User user = TestValues.getUserObject();
        AccessDetails personalDetails = TestValues.getAccessDetailsObject();
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));


        sut.updateAccessDetails(personalDetails, user.getId());

        Mockito.verify(userRepository).save(user);
    }

    @Test
    void remove() {
        User user = TestValues.getUserObject();

        sut.remove(user.getId());

        Mockito.verify(userRepository).deleteById(Mockito.any(Long.class));
    }

    @Test
    void findById() {
        User user = TestValues.getUserObject();
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(user));

        sut.findById(user.getId());

        Mockito.verify(userRepository).findById(Mockito.any(Long.class));
    }
}