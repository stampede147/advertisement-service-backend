package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.FeedbackResponseAdvertisementDto;
import com.evgeniykudashov.adservice.dto.response.FeedbackResponseDto;
import com.evgeniykudashov.adservice.dto.response.FeedbackResponseUserDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.feedback.Feedback;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class FeedbackMapper {


    @Mapping(target = "feedbackId", source = "id")
    public abstract FeedbackResponseDto toFeedbackResponseDto(Feedback feedback);

    public abstract PageDto<FeedbackResponseDto> toPageDto(Page<Feedback> feedbacks);

    @Mapping(target = "userId", source = "id")
    protected abstract FeedbackResponseUserDto toFeedbackResponseUserDto(User user);

    @Mapping(target = "advertisementId", source = "id")
    protected abstract FeedbackResponseAdvertisementDto toFeedbackResponseAdvertisementDto(Advertisement advertisement);
}
