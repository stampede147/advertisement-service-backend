package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByRecipientId(Long recipientUserId);

    List<Feedback> findAllBySenderId(Long senderUserId);
}
