package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findReviewsByBuyerId(long buyerId, Pageable pageable);

    Page<Review> findReviewsByRecipientId(long recipientId, Pageable pageable);
}
