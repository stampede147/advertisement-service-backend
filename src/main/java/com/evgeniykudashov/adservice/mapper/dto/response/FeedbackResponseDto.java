package com.evgeniykudashov.adservice.mapper.dto.response;

import com.evgeniykudashov.adservice.model.FeedbackStatus;
import com.evgeniykudashov.adservice.model.Mark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackResponseDto {

    private long feedbackId;

    private FeedbackResponseAdvertisementDto advertisement;

    private FeedbackResponseUserDto seller;

    private FeedbackResponseUserDto customer;

    private Mark mark;

    private FeedbackStatus status;
}
