package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FeedbackRepository extends ListCrudRepository<Feedback, Long> {

    List<Feedback> findAllByRecipientId(Long recipientUserId);

    List<Feedback> findAllBySenderId(Long senderUserId);
}
