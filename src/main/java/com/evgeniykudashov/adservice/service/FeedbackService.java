package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.view.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    FeedbackDto create(FeedbackDto feedbackDto);

    FeedbackDto update(FeedbackDto feedbackDto);

    void remove(FeedbackDto feedbackDto);

    List<FeedbackDto> findAllByUserRecipient(long userRecipient);

    List<FeedbackDto> findAllByUserSender(long userRecipient);


}
