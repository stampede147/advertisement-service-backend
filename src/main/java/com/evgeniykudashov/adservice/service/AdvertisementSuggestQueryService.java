package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;

public interface AdvertisementSuggestQueryService {

    AdvertisementSuggestResponseDto suggestAdvertisements(AdvertisementSuggestRequestDto requestDto, int size);
}
