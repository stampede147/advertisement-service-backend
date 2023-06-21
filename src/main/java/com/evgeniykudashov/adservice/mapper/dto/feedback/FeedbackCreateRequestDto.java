package com.evgeniykudashov.adservice.mapper.dto.feedback;


import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class FeedbackCreateRequestDto {

    Long advertisementId;

    Long senderUserId;

    Long recipientUserId;

    Mark mark;

    Description description;

}
