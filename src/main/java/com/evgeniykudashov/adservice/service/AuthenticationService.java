package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.request.UsernamePasswordRequestDto;

public interface AuthenticationService {

    String generateJwtToken(UsernamePasswordRequestDto dto);

}
