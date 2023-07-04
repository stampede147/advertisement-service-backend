package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.request.FeedbackRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.FeedbackResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    long createFeedback(FeedbackRequestDto dto);

    void deleteFeedback(long feedbackId);

    PageDto<FeedbackResponseDto> findBySellerId(long userId, Pageable pageable);

    PageDto<FeedbackResponseDto> findByCustomerId(long userId, Pageable pageable);

}
