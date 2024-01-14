package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewAdvertisementResponse;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface AdvertisementMapper {

    Advertisement toAdvertisement(AdvertisementRequestDto dto, Principal principal);

    AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    List<AdvertisementResponseDto> toResponseDto(List<Advertisement> advertisements);

    PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementsPage);

    ReviewAdvertisementResponse toReviewAdvertisementResponse(Advertisement advertisement);
}
