package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;

import java.time.LocalDateTime;

public interface AuthenticationService {

    String generateJwtToken(UsernamePasswordRequestDto dto);

    String generateRefreshToken(String accessToken);

    String generateAccessToken(String refreshTokenId, LocalDateTime callTime);

}
