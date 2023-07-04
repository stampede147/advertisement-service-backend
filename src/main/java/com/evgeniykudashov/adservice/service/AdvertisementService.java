package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

public interface AdvertisementService {

    long create(AdvertisementRequestDto advertisement);

    void remove(long advertisementId);

    PageDto<AdvertisementResponseDto> findAllByUserId(long userId, Pageable pageable);

    AdvertisementResponseDto findById(long advertisementId);


}
