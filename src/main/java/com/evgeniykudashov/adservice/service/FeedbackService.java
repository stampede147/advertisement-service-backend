package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;

import java.util.List;

public interface FeedbackService {

    long create(Feedback feedback);

    void remove(long feedbackId);

    void updateMark(Mark mark, long feedbackId);

    void updateDescription(Description description, long feedbackId);

    List<Feedback> findAllByRecipientUserId(long recipientUserId);

    List<Feedback> findAllBySenderUserId(long senderUserId);


}
