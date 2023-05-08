package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundFeedbackException;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Override
    public long create(Feedback feedback) {
        return feedbackRepository.save(feedback).id;
    }

    @Override
    public void updateMark(Mark mark, long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(NotFoundFeedbackException::new);
        feedback.updateMark(mark);
        feedbackRepository.save(feedback);
    }

    @Override
    public void updateDescription(Description description, long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(NotFoundFeedbackException::new);
        feedback.updateDescription(description);
        feedbackRepository.save(feedback);
    }

    @Override
    public void remove(long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public List<Feedback> findAllByRecipientUserId(long recipientUserId) {
        return feedbackRepository.findAllByRecipientId(recipientUserId);
    }

    @Override
    public List<Feedback> findAllBySenderUserId(long senderUserId) {
        return feedbackRepository.findAllBySenderId(senderUserId);
    }
}
