package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.FeedbackRequestDto;
import com.evgeniykudashov.adservice.dto.response.FeedbackResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.FeedbackMapper;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    private final FeedbackMapper dtoMapper;


    @Override
    @Transactional
    public long createFeedback(FeedbackRequestDto dto) {
        log.trace("Started createFeedback(FeedbackRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        return feedbackRepository.save(dtoMapper.toFeedback(dto))
                .getId();
    }

    @Override
    @Transactional
    public void deleteFeedback(long feedbackId) {
        log.trace("Started deleteFeedback(long) method");
        log.debug("Provided parameter feedbackId: {}", feedbackId);

        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<FeedbackResponseDto> findBySellerId(long userId, Pageable pageable) {
        log.trace("Started findBySellerId(long, Pageable) method");
        log.debug("Provided parameters userId: {}, pageable: {}", userId, pageable);

        return dtoMapper.toPageDto(feedbackRepository.findBySellerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<FeedbackResponseDto> findByCustomerId(long userId, Pageable pageable) {
        log.trace("Started findByCustomerId(long, Pageable) method");
        log.debug("Provided parameters userId: {}, pageable: {}", userId, pageable);

        return dtoMapper.toPageDto(feedbackRepository.findByCustomerId(userId, pageable));
    }
}
