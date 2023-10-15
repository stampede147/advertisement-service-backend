package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewedAdvertisementRepository extends JpaRepository<ViewedAdvertisement, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"advertisement",
            "advertisement.location", "advertisement.seller", "advertisement.images"})
    List<ViewedAdvertisement> findViewedAdvertisementByUserId(long userId, Pageable pageable);
}
