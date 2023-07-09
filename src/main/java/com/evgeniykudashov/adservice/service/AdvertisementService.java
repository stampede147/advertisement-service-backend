package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

public interface AdvertisementService {

    long createAdvertisement(AdvertisementRequestDto advertisement);

    void removeAdvertisementById(long advertisementId);

    PageDto<? extends AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable);


    AdvertisementResponseDto getOneByAdvertisementId(long advertisementId);


}
