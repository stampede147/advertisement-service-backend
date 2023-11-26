package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;
import org.springframework.data.domain.Pageable;

public interface AdvertisementSuggestQueryService {

    AdvertisementSuggestResponseDto suggestAdvertisements(AdvertisementSuggestRequestDto requestDto, Pageable pageable);
}
