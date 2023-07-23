package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.exception.servicelayer.PasswordMismatchException;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.security.jwt.tokenfactory.OAuthTokenFactory;
import com.evgeniykudashov.adservice.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    OAuthTokenFactory factory;

    @InjectMocks
    AuthenticationServiceImpl sut;

    @Test
    void return_token_for_authentication() {
        //configuring password encoder
        Mockito.when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        Mockito.when(userRepository.findByUsername(Mockito.any()))
                .thenReturn(Optional.ofNullable(Mockito.mock(User.class)));

        String expected = "token";
        Mockito.when(factory.createToken(Mockito.any(), Mockito.any())).thenReturn(expected);

        UsernamePasswordRequestDto dto = new UsernamePasswordRequestDto("username", "password");

        //act
        String result = sut.generateJwtToken(dto);

        //assert
        Assertions.assertEquals(expected, result);

    }

    @Test
    void throw_exception_when_not_found_entity_with_username() {
        //arrange
        Optional<User> empty = Optional.empty();
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(empty);
        UsernamePasswordRequestDto dto = new UsernamePasswordRequestDto("username", "password");

        //act and assert
        Assertions.assertThrows(NotFoundEntityException.class, () -> sut.generateJwtToken(dto));
    }

    @Test
    void throw_exception_when_provided_wrong_password_for_authentication() {
        //configuring password encoder
        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenAnswer(answer -> {
            Object argument0 = answer.getArgument(0);
            Object argument1 = answer.getArgument(1);
            return Objects.equals(argument0, argument1);
        });
        //configuring returning valid user
        User user = Mockito.mock(User.class);
        Mockito.when(user.getPassword()).thenReturn("password");
        Optional<User> validUser = Optional.ofNullable(user);
        Mockito.when(userRepository.findByUsername(Mockito.any())).thenReturn(validUser);

        UsernamePasswordRequestDto dto = new UsernamePasswordRequestDto("username", "wrong");

        //act and assert
        Assertions.assertThrows(PasswordMismatchException.class, () -> sut.generateJwtToken(dto));

    }
}