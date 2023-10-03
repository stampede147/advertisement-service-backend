package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface AdvertisementService {

    long createAdvertisementByPrincipal(Principal principal, AdvertisementRequestDto dto);

    void removeAdvertisementById(long advertisementId);

    PageDto<AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable);

    PageDto<AdvertisementResponseDto> getPageByPrincipal(Principal principal, Pageable pageable);

    AdvertisementResponseDto getOneByAdvertisementId(long advertisementId);

    void activeAdvertisementById(long id);

    void hideAdvertisementById(long id);

}
