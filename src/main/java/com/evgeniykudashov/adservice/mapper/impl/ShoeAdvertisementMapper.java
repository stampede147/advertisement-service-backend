package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.request.ShoeAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ShoeAdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.ShoeAdvertisement;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.repository.ThingColorRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", imports = ShoeSize.class)
@Setter(onMethod_ = @Autowired)
public abstract class ShoeAdvertisementMapper implements AdvertisementMapper {

    protected UserRepository userRepository;

    protected ThingColorRepository thingColorRepository;

    @Override
    public ShoeAdvertisement toAdvertisement(AdvertisementRequestDto dto) {
        return toAdvertisement1((ShoeAdvertisementRequestDto) dto);
    }

    @Mapping(target = "id", source = "dto.advertisementId")
    @Mapping(target = "owner", expression = "java(userRepository.getReferenceById(dto.getUserId()))")
    @Mapping(target = "color", expression = "java(thingColorRepository.getReferenceById(dto.getColor()))")
    @Mapping(target = "size", expression = "java(new ShoeSize(dto.getSize()))")
    public abstract ShoeAdvertisement toAdvertisement1(ShoeAdvertisementRequestDto dto);

    @Override
    public ShoeAdvertisementResponseDto toResponseDto(Advertisement advertisement) {
        return toResponseDto1((ShoeAdvertisement) advertisement);
    }

    public abstract ShoeAdvertisementResponseDto toResponseDto1(ShoeAdvertisement advertisement);

    @Override
    public PageDto<? extends AdvertisementResponseDto> toPageDto(Page<? extends Advertisement> page) {
        return null;
    }
}
