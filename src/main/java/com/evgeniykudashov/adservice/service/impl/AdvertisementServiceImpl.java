package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperProxy;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)

@Slf4j
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementMapperProxy advertisementMapperProxy;

    @Override
    @Transactional
    public long createAdvertisement(AdvertisementRequestDto dto) {
        log.trace("Started createAdvertisement(AdvertisementRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        Advertisement advertisement = advertisementMapperProxy.toAdvertisement(dto);
        return advertisementRepository.save(advertisement).getId();
    }

    @Transactional
    public void removeAdvertisementById(long advertisementId) {
        log.trace("Started method removeAdvertisementById(long)");
        log.debug("Provided parameter advertisementId: {}", advertisementId);

        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    public PageDto<AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable) {
        log.trace("Started getPageByUserId(long, Pageable) method");
        log.debug("Provided parameters userId: {}, pageable: {}", userId, pageable);

        return (PageDto<AdvertisementResponseDto>) advertisementMapperProxy.toPageDto(advertisementRepository.findAllByOwnerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementResponseDto getOneByAdvertisementId(long advertisementId) {
        log.trace("Started getOneByAdvertisementId(long) method");
        log.debug("Provided parameters advertisementId: {}", advertisementId);

        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> {
                    NotFoundEntityException notFoundEntityException = new NotFoundEntityException();
                    log.error("Entity not found", notFoundEntityException);
                    return notFoundEntityException;
                });

        return advertisementMapperProxy.toResponseDto(advertisement);
    }

}
