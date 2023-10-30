package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewResponseDto;
import com.evgeniykudashov.adservice.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring", uses = AdvertisementMapper.class)
public abstract class ReviewMapper {


    @Mapping(target = "rate", expression = "java(review.getRate().ordinal())")
    public abstract ReviewResponseDto toReviewResponseDto(Review review);

    public abstract PageDto<ReviewResponseDto> toPageDto(Page<Review> reviewPage);
}
