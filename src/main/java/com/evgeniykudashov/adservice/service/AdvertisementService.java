package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Title;

import java.util.List;

public interface AdvertisementService {

    Advertisement create(Advertisement advertisement);

    Advertisement updateTitle(Title title, long advertisementId);

    Advertisement updateAddress(Address address, long advertisementId);

    Advertisement updateStatus(AdvertisementStatus status, long advertisementId);

    void remove(long advertisementId);

    public List<Advertisement> findAllByUserId(long userId);

    public List<Advertisement> findAllByTitle(Title title);

    public List<Advertisement> findAllByCategoryId(long categoryId);


}
