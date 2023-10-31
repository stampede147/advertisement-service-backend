package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.request.ReviewRequestDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewResponseDto;
import com.evgeniykudashov.adservice.model.Review;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.time.LocalDate;


@Mapper(componentModel = "spring", uses = AdvertisementMapper.class)
public abstract class ReviewMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "advertisement", expression = "java(advertisementRepository.findById(reviewDto.getAdvertisementId()).orElseThrow(com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException::new))")
    @Mapping(target = "buyer", expression = "java(userRepository.getReferenceById(buyerUserId))")
    public abstract Review toReview(ReviewRequestDto reviewDto,
                                    long buyerUserId,
                                    LocalDate createdAt,
                                    AdvertisementRepository advertisementRepository,
                                    UserRepository userRepository);

    @Mapping(target = "rate", expression = "java(review.getRate().ordinal())")
    public abstract ReviewResponseDto toReviewResponseDto(Review review);

    public abstract PageDto<ReviewResponseDto> toPageDto(Page<Review> reviewPage);
}
