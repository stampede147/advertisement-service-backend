package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("from Feedback f where f.seller.id = :userId")
    Page<Feedback> findBySellerId(long userId, Pageable pageable);

    @Query("from Feedback f where f.customer.id = :customerId")
    Page<Feedback> findByCustomerId(long customerId, Pageable pageable);
}
