package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.request.ClothingAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.ClothingAdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.ClothingAdvertisement;
import com.evgeniykudashov.adservice.repository.ClothingSizeRepository;
import com.evgeniykudashov.adservice.repository.ThingColorRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.SubclassMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class ClothingAdvertisementMapper implements AdvertisementMapper {

    protected ThingColorRepository thingColorRepository;

    protected UserRepository userRepository;
    protected ClothingSizeRepository clothingSizeRepository;

    @Override
    public ClothingAdvertisement toAdvertisement(@MappingTarget AdvertisementRequestDto dto) {
        return toAdvertisement1(((ClothingAdvertisementRequestDto) dto));
    }

    @Mapping(target = "color", expression = "java(thingColorRepository.getReferenceById(dto.getColor()))")
    @Mapping(target = "size", expression = "java(clothingSizeRepository.getReferenceById(dto.getSize()))")
    @Mapping(target = "type", source = "dto.type")
    @Mapping(target = "clothingType", source = "dto.clothingType")
    @Mapping(target = "owner", expression = "java(userRepository.getReferenceById(dto.getUserId()))")
    protected abstract ClothingAdvertisement toAdvertisement1(ClothingAdvertisementRequestDto dto);


    @Override
    public ClothingAdvertisementResponseDto toResponseDto(Advertisement advertisement) {
        return toResponseDto1((ClothingAdvertisement) advertisement);
    }

    @Mapping(target = "color", source = "advertisement.color.color")
    @Mapping(target = "userId", source = "advertisement.owner.id")
    @Mapping(target = "size", source = "advertisement.size.size")
    protected abstract ClothingAdvertisementResponseDto toResponseDto1(ClothingAdvertisement advertisement);

    @Override
    @SubclassMapping(source = ClothingAdvertisement.class, target = ClothingAdvertisementResponseDto.class)
    public PageDto<? extends AdvertisementResponseDto> toPageDto(Page<? extends Advertisement> page) {
        return toPageDto1((Page<ClothingAdvertisement>) page);
    }

    protected abstract PageDto<ClothingAdvertisementResponseDto> toPageDto1(Page<ClothingAdvertisement> page);
}
