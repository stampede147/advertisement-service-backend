package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findReviewsByBuyerId(long buyerId, Pageable pageable);


    @Query("from Review r join r.advertisement ra where ra.seller.id = :sellerId")
    Page<Review> findReviewsBySellerId(long sellerId, Pageable pageable);
}
