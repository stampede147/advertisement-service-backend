package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.UsernamePasswordDto;

public interface AuthenticationService {

    String generateJwtToken(UsernamePasswordDto dto);

}
