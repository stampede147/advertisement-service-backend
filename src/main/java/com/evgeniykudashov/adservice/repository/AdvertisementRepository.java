package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"location"})
    Page<Advertisement> findAllBySellerId(long sellerId, Pageable pageable);

}
