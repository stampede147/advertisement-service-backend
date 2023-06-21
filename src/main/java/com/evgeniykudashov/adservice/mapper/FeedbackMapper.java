package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.feedback.FeedbackCreateRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.feedback.FeedbackResponseDto;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


@MapperConfig
@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired, value = AccessLevel.PROTECTED)
public abstract class FeedbackMapper {

    protected FeedbackRepository feedbackRepository;

    protected UserRepository userRepository;

    protected AdvertisementRepository advertisementRepository;


    @Mapping(target = "senderId", expression = "java(feedback.getSender().getId())")
    @Mapping(target = "recipientId", expression = "java(feedback.getRecipient().getId())")
    @Mapping(target = "advertisementId", expression = "java(feedback.getAdvertisement().getId())")
    public abstract FeedbackResponseDto toFeedbackResponseDto(Feedback feedback);

    public abstract Collection<FeedbackResponseDto> toFeedbackResponseDto(Collection<Feedback> collection);

    public abstract Feedback toFeedback(FeedbackCreateRequestDto dto);

    @Named("advertisementFromAdvertisementId")
    protected Advertisement advertisementFromAdvertisementId(Long advertisementId) {
        return advertisementRepository.getReferenceById(advertisementId);
    }

    @Named("userFromUserId")
    protected User userFromUserId(Long userId) {
        return userRepository.getReferenceById(userId);
    }

}
