package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.mapper.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class AdvertisementMapper {

    protected UserRepository userRepository;

    @Mapping(target = "owner", expression = "java(userRepository.getReferenceById(requestDto.getUserId()))")
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto requestDto);


    @Mapping(target = "userId", expression = "java(advertisement.getOwner().getId())")
    @Mapping(target = "advertisementId", source = "id")
    public abstract AdvertisementResponseDto toAdvertisementResponseDto(Advertisement advertisement);

    public abstract PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementPage);


}
