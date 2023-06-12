package com.evgeniykudashov.adservice.controller.mapper;


import com.evgeniykudashov.adservice.controller.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.controller.mapper.dto.advertisement.AdvertisementCreateRequestDto;
import com.evgeniykudashov.adservice.controller.mapper.dto.advertisement.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.controller.mapper.dto.advertisement.AdvertisementUpdateRequestDto;
import com.evgeniykudashov.adservice.controller.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.controller.repository.CategoryRepository;
import com.evgeniykudashov.adservice.controller.repository.UserRepository;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Collection;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class AdvertisementMapper {
    protected CategoryRepository categoryRepository;
    protected UserRepository userRepository;
    protected AdvertisementRepository advertisementRepository;

    @Mapping(target = "category", expression = "java(categoryRepository.getReferenceById(requestDto.getCategoryId()))")
    @Mapping(target = "owner", expression = "java(userRepository.getReferenceById(requestDto.getUserId()))")
    public abstract Advertisement toAdvertisement(AdvertisementCreateRequestDto requestDto);

    public abstract Advertisement toAdvertisement(AdvertisementUpdateRequestDto requestDto);


    @Mapping(target = "userId", expression = "java(advertisement.getOwner().getId())")
    @Mapping(target = "categoryId", expression = "java(advertisement.getCategory().getId())")
    @Mapping(target = "advertisementId", expression = "java(advertisement.getId())")
    public abstract AdvertisementResponseDto toAdvertisementResponseDto(Advertisement advertisement);

    public abstract Collection<AdvertisementResponseDto> toAdvertisementResponseDto(Collection<Advertisement> advertisement);


    public abstract PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementPage);


}
