package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.helper.ReflectionUtility;
import com.evgeniykudashov.adservice.helper.StringUtils;
import com.evgeniykudashov.adservice.model.domain.DomainLayerConstants;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public long create(Feedback feedback) {
        return feedbackRepository.save(feedback).getId();
    }

    @Override
    @Transactional
    public void patchUpdate(Map<String, Object> map, long feedbackId) {

        map.forEach((k, v) -> ReflectionUtility.callMethod(this.findById(feedbackId),
                DomainLayerConstants.UPDATE_METHOD_PREFIX.concat(StringUtils.capitalize(k)),
                v));
    }

    @Transactional
    @Override
    public void remove(long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Feedback> findAllByRecipientUserId(long recipientUserId) {
        return feedbackRepository.findAllByRecipientId(recipientUserId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Feedback> findAllBySenderUserId(long senderUserId) {
        return feedbackRepository.findAllBySenderId(senderUserId);
    }

    @Transactional(readOnly = true)
    @Override
    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(NotFoundEntityException::new);
    }
}
