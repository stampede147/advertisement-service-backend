package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public abstract class AdvertisementMapper {


    @Mapping(target = "startTime", expression = "java(startTime)")
    @Mapping(target = "status", expression = "java(status)")
    @Mapping(target = "seller", expression = "java(seller)")
    @Mapping(target = "id", ignore = true)
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto,
                                                  LocalDate startTime,
                                                  AdvertisementStatus status,
                                                  User seller);

    public abstract AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    public abstract PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementsPage);
}
