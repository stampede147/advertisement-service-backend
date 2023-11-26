package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewedAdvertisementRepository extends JpaRepository<ViewedAdvertisement, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"advertisement",
            "advertisement.location", "advertisement.seller", "advertisement.images"})
    List<ViewedAdvertisement> findViewedAdvertisementByUserId(long userId, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"advertisement"})
    @Query("SELECT new ViewedAdvertisement(max(va.id), va.advertisement, va.user) from ViewedAdvertisement va  WHERE va.advertisement.title LIKE %:title% and va.user.id = :userId GROUP BY va.advertisement, va.user ")
    List<ViewedAdvertisement> findViewedAdvertisementsByTitleContains(long userId, String title, Pageable pageable);
}
