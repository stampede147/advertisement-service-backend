package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.LayoutRequestDto;
import com.evgeniykudashov.adservice.dto.response.LayoutResponseDto;

public interface EditAdvertisementLayoutService {


    LayoutResponseDto getLayout(LayoutRequestDto dto);
}
