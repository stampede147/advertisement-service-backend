package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.response.EditLayoutResponseDto;
import com.evgeniykudashov.adservice.dto.response.NavigationResponseDto;

public interface AdvertisementEditService {

    NavigationResponseDto loadNavigation();

    EditLayoutResponseDto loadLayout(NavigationResponseDto.NavigationDto navigationDto);
}
