package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.ReviewRequestDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewResponseDto;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface ReviewService {

    void create(ReviewRequestDto dto, Principal principal);

    void delete(Long reviewId);


    PageDto<ReviewResponseDto> getReceivedReviews(Principal principal, Pageable pageable);

    PageDto<ReviewResponseDto> getSentReviews(Principal principal, Pageable pageable);


}
