package com.evgeniykudashov.adservice.controller.service.impl;

import com.evgeniykudashov.adservice.controller.exception.NotFoundAdvertisementException;
import com.evgeniykudashov.adservice.controller.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.controller.service.AdvertisementService;
import com.evgeniykudashov.adservice.controller.service.helper.ReflectionUtility;
import com.evgeniykudashov.adservice.controller.service.helper.StringUtils;
import com.evgeniykudashov.adservice.model.domain.DomainLayerConstants;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@AllArgsConstructor(onConstructor_ = @Autowired)

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Override
    public long create(Advertisement advertisement) {
        return advertisementRepository.save(advertisement).getId();
    }

    @Transactional
    @Override
    public void patchUpdate(Map<String, Object> data, long advertisementId) {
        data.forEach((k, v) -> ReflectionUtility.callMethod(this.findById(advertisementId),
                DomainLayerConstants.UPDATE_METHOD_PREFIX.concat(StringUtils.capitalize(k)),
                v));
    }

    @Transactional
    public void remove(long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Advertisement> findAllByUserId(long userId, Pageable pageable) {
        return advertisementRepository.findAllByOwnerId(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement findById(long advertisementId) {
        return advertisementRepository.findById(advertisementId).orElseThrow(NotFoundAdvertisementException::new);
    }


//    public List<Advertisement> findAll(){
//        return advertisementRepository.findAll(pageable).getContent();
//    }

}
