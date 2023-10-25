package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerUnitTest {

    @Mock
    AuthenticationService service;

    @InjectMocks
    JwtAuthenticationController sut;

    @Test
    void createJwtAuthentication() {
        // Arrange
        UsernamePasswordRequestDto dto = Mockito.mock(UsernamePasswordRequestDto.class);

        // Act
        ResponseEntity<?> jwtAuthentication = sut.provideJwtAuthentication(dto);

        // Assert
        Assertions.assertNotNull(jwtAuthentication);
        Mockito.verify(service).generateJwtToken(dto);
    }
}