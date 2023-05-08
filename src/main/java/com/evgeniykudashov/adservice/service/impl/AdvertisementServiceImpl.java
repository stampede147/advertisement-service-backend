package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundAdvertisementException;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Override
    public long create(Advertisement advertisement) {
        return advertisementRepository.save(advertisement).getId();
    }

    @Override
    @Transactional
    public void updateTitle(Title title, long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(NotFoundAdvertisementException::new);
        advertisement.updateTitle(title);
        advertisementRepository.save(advertisement);
    }

    @Override
    @Transactional
    public void updateAddress(Address address, long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(NotFoundAdvertisementException::new);
        advertisement.updateAddress(address);
        advertisementRepository.save(advertisement);
    }

    @Override
    @Transactional
    public void updateStatus(AdvertisementStatus status, long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(NotFoundAdvertisementException::new);
        advertisement.updateStatus(status);
        advertisementRepository.save(advertisement);
    }

    @Override
    @Transactional
    public void remove(long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> findAllByUserId(long userId) {
        return advertisementRepository.findAllByOwnerId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> findAllByTitle(Title title) {
        return advertisementRepository.findAllByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> findAllByCategoryId(long categoryId) {
        return advertisementRepository.findAllByCategoryId(categoryId);
    }
}
