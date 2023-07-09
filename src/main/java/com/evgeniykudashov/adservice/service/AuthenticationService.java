package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;

public interface AuthenticationService {

    String generateJwtToken(UsernamePasswordRequestDto dto);

}
