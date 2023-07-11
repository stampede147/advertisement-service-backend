package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.FeedbackRequestDto;
import com.evgeniykudashov.adservice.dto.response.FeedbackResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.FeedbackMapper;
import com.evgeniykudashov.adservice.model.feedback.Feedback;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    private final FeedbackMapper dtoMapper;


    @Override
    @Transactional
    public long createFeedback(FeedbackRequestDto dto) {
        return feedbackRepository.save(Feedback.builder()
                .id(0)
                .description(dto.getDescription())
                .advertisement(advertisementRepository.getReferenceById(dto.getAdvertisementId()))
                .mark(dto.getMark())
                .status(dto.getStatus())
                .seller(userRepository.getReferenceById(dto.getSellerId()))
                .customer(userRepository.getReferenceById(dto.getCustomerId()))
                .build()).getId();
    }

    @Override
    @Transactional
    public void deleteFeedback(long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<FeedbackResponseDto> findBySellerId(long userId, Pageable pageable) {
        return dtoMapper.toPageDto(feedbackRepository.findBySellerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<FeedbackResponseDto> findByCustomerId(long userId, Pageable pageable) {
        return dtoMapper.toPageDto(feedbackRepository.findByCustomerId(userId, pageable));
    }
}
