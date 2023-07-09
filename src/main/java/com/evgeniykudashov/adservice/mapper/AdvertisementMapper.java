package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import org.springframework.data.domain.Page;

public interface AdvertisementMapper {


    Advertisement toAdvertisement(AdvertisementRequestDto dto);

    AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    PageDto<? extends AdvertisementResponseDto> toPageDto(Page<? extends Advertisement> page);


}
