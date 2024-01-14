package com.evgeniykudashov.adservice.mapper.internalmapping;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;

public interface AdvertisementMapperHandler extends EntityMapperHandler<Advertisement, AdvertisementRequestDto, AdvertisementResponseDto> {


    void toEntity(Advertisement a, AdvertisementRequestDto rtd);

    void toDto(AdvertisementResponseDto red, Advertisement a);
}
