package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.ReviewRequestDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewResponseDto;
import com.evgeniykudashov.adservice.mapper.ReviewMapper;
import com.evgeniykudashov.adservice.model.Review;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ReviewRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewServiceImpl implements ReviewService {

    private final AdvertisementRepository advertisementRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final Converter<Principal, Long> principalConverter;

    private final ReviewMapper reviewMapper;


    @Override
    @Transactional
    public void create(ReviewRequestDto dto, Principal principal) {

        Review review = reviewMapper.toReview(dto,
                principalConverter.convert(principal),
                LocalDate.now(),
                advertisementRepository,
                userRepository);

        reviewRepository.save(review);

    }

    @Override
    @Transactional
    public void delete(Long reviewId) {

        userRepository.delete(userRepository.getReferenceById(reviewId));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<ReviewResponseDto> getReceivedReviews(Principal principal, Pageable pageable) {

        Page<Review> reviews = reviewRepository.findReviewsBySellerId(principalConverter.convert(principal), pageable);
        return reviewMapper.toPageDto(reviews);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<ReviewResponseDto> getSentReviews(Principal principal, Pageable pageable) {

        Page<Review> reviews = reviewRepository.findReviewsByBuyerId(principalConverter.convert(principal), pageable);
        return reviewMapper.toPageDto(reviews);
    }
}
