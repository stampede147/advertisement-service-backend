package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public abstract class DefaultAdvertisementMapper implements AdvertisementMapper {

    protected UserRepository userRepository;

    @Override
    @Mapping(target = "owner", expression = "java(userRepository.getReferenceById(dto.getUserId()))")
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto);

    @Mapping(target = "userId", source = "advertisement.owner.id")
    @Override
    public abstract AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    @Override
    public PageDto<? extends AdvertisementResponseDto> toPageDto(Page<? extends Advertisement> page) {
        return toPageDto1((Page<Advertisement>) page);
    }

    public abstract PageDto<AdvertisementResponseDto> toPageDto1(Page<Advertisement> page);
}
