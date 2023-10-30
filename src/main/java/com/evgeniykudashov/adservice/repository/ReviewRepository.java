package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
