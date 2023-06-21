package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackService {

    long create(Feedback feedback);

    void patchUpdate(Map<String, Object> map, long feedbackId);

    void remove(long feedbackId);

    List<Feedback> findAllByRecipientUserId(long recipientUserId);

    List<Feedback> findAllBySenderUserId(long senderUserId);

    Feedback findById(Long id);
}
