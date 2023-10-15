package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"location", "seller", "images"})
    Optional<Advertisement> findById(Long aLong);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"location"})
    Page<Advertisement> findAllBySellerIdAndStatus(long sellerId, AdvertisementStatus status, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"images", "seller", "location"})
    Page<Advertisement> findAdvertisementsBySellerId(long sellerId, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"location", "images", "category", "seller"})
    @Query("select a from Advertisement a where a.title like %:title%")
    Page<Advertisement> findAllByTitle(String title, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"location", "images", "category", "seller"})
    @Query("select a from Advertisement a where a.title like %:title% and a.category.id=:categoryId")
    Page<Advertisement> findAllByTitleAndCategoryId(String title, long categoryId, Pageable pageable);

    @Query("select a from Advertisement a where a.id=:advertisementId and a.seller.id = :sellerId")
    Optional<Advertisement> findByAdvertisementIdAndSellerId(long advertisementId, long sellerId);

    @Query("select a from ViewedAdvertisement va " +
            "join Advertisement a on va.advertisement = a " +
            "where va.user.id = :userId " +
            "group by  va.advertisement " +
            "order by max(va.id) desc")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"seller", "location"})
    List<Advertisement> findViewedAdvertisementsByUserId(long userId, Pageable pageable);
}
