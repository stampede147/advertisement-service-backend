package com.evgeniykudashov.adservice.controller.mapper.dto.feedback;


import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class FeedbackResponseDto {

    long id;

    long advertisementId;

    long senderId;

    long recipientId;

    Mark mark;

    Description description;


}
