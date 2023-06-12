package com.evgeniykudashov.adservice.controller.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findAllByOwnerId(long ownerUserId);

    Page<Advertisement> findAllByOwnerId(long ownerUserId, Pageable pageable);

    List<Advertisement> findAllByCategoryId(long categoryId);

    List<Advertisement> findAllByTitle(Title title);


}
