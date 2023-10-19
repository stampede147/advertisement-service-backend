package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.exception.servicelayer.PasswordMismatchException;
import com.evgeniykudashov.adservice.model.user.Role;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    OAuthTokenFactory tokenFactory;

    @InjectMocks
    AuthenticationServiceImpl sut;

    @Test
    void generateJwtToken_should_return_valid_token_when_username_and_password_are_correct() {
        // Arrange
        String username = "testuser";
        String rawPassword = "testpassword";
        String encodedPassword = "encodedpassword";
        String token = "generatedtoken";

        UsernamePasswordRequestDto requestDto = new UsernamePasswordRequestDto(username, rawPassword);

        User user = Mockito.mock(User.class);
        Mockito.when(user.getPassword()).thenReturn(encodedPassword);

        Mockito.when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword))
                .thenReturn(true);
        Mockito.when(tokenFactory.createToken(Mockito.any(), Mockito.any()))
                .thenReturn(token);

        // Act
        String resultToken = sut.generateJwtToken(requestDto);

        // Assert
        Assertions.assertEquals(token, resultToken);
    }

    @Test
    void generateJwtToken_should_throw_NotFoundEntityException_when_user_not_found() {
        // Arrange
        String username = "testuser";
        String rawPassword = "testpassword";

        UsernamePasswordRequestDto requestDto = new UsernamePasswordRequestDto(username, rawPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundEntityException.class, () -> sut.generateJwtToken(requestDto));
        Mockito.verify(userRepository).findByUsername(username);
        Mockito.verify(passwordEncoder, Mockito.never())
                .matches(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(tokenFactory, Mockito.never())
                .createToken(Mockito.anyString(), Mockito.anySet());
    }

    @Test
    void generateJwtToken_should_throw_PasswordMismatchException_when_password_is_wrong() {
        // Arrange
        String username = "testuser";
        String rawPassword = "testpassword";
        String encodedPassword = "encodedpassword";

        UsernamePasswordRequestDto requestDto = new UsernamePasswordRequestDto(username, rawPassword);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // Act & Assert
        assertThrows(PasswordMismatchException.class, () -> sut.generateJwtToken(requestDto));
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
        verify(tokenFactory, never()).createToken(anyString(), anySet());
    }
}