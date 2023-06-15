package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdvertisementService {

    long create(Advertisement advertisement);

    void patchUpdate(Map<String, Object> data, long advertisementId);

    void remove(long advertisementId);

    Page<Advertisement> findAllByUserId(long userId, Pageable pageable);

    Advertisement findById(long advertisementId);


}
