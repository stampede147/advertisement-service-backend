package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewAdvertisementResponse;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.service.factory.AdvertisementFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Primary
@Component
public class AdvertisementMapperImpl implements AdvertisementMapper {

    private final AdvertisementFactory factory;

    private final AdvertisementMapperHandler handler;

    @Autowired
    public AdvertisementMapperImpl(AdvertisementFactory factory, AdvertisementMapperHandler handler) {
        this.factory = factory;
        this.handler = handler;
    }


    @Override
    public Advertisement toAdvertisement(AdvertisementRequestDto dto, Principal principal) {

        Advertisement advertisement = factory.createAdvertisementForType(dto.getType(), principal);

        handler.toEntity(advertisement, dto);

        return advertisement;
    }

    @Override
    public AdvertisementResponseDto toResponseDto(Advertisement advertisement) {

        AdvertisementResponseDto responseDto = factory.createAdvertisementResponseDtoForType(advertisement.getType());

        handler.toDto(responseDto, advertisement);

        return responseDto;
    }

    @Override
    public List<AdvertisementResponseDto> toResponseDto(List<Advertisement> advertisements) {
        return advertisements.stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementsPage) {

        PageDto<AdvertisementResponseDto> pageDto = new PageDto<>();

        pageDto.setContent(toResponseDto(advertisementsPage.getContent()));
        pageDto.setTotalPages(advertisementsPage.getTotalPages());
        pageDto.setTotalElements(advertisementsPage.getTotalElements());
        pageDto.setSize(advertisementsPage.getSize());
        pageDto.setNumber(advertisementsPage.getNumber());

        return pageDto;
    }

    @Override
    public ReviewAdvertisementResponse toReviewAdvertisementResponse(Advertisement advertisement) {

        ReviewAdvertisementResponse response = new ReviewAdvertisementResponse();

        response.setId(advertisement.getId().toString());
        response.setSeller(advertisement.getSeller().getFirstName());
        response.setTitle(advertisement.getTitle());

        return response;
    }


}
