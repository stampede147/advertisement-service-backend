package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findAllByOwnerId(long ownerUserId);

    List<Advertisement> findAllByCategoryId(long categoryId);

    List<Advertisement> findAllByTitle(Title title);


}
