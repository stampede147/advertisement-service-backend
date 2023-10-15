package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

public interface SearchAdvertisementService {

    PageDto<AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable);

    AdvertisementResponseDto getOneByAdvertisementId(long advertisementId);

    PageDto<AdvertisementResponseDto> findAdvertisementsByName(String key, Pageable pageable);

    PageDto<AdvertisementResponseDto> findAdvertisementsByNameAndCategoryId(String key, Long categoryId, Pageable pageable);

}
