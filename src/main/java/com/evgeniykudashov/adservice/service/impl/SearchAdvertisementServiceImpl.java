package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.SearchAdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)

@Slf4j
@Service
public class SearchAdvertisementServiceImpl implements SearchAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementMapper advertisementMapper;

    /*
     * Returns advertisements with status ACTIVE that corresponds provided user id
     *
     * @param userId
     * @param pageable
     * @see com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus
     */
    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable) {
        log.trace("Started getPageByUserId(long, Pageable) method");
        log.debug("Provided parameters userId: {}, pageable: {}", userId, pageable);

        Page<Advertisement> allBySellerIdAAndStatus = advertisementRepository.findAllBySellerIdAndStatus(userId, AdvertisementStatus.ACTIVE, pageable);

        return advertisementMapper.toPageDto(allBySellerIdAAndStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementResponseDto getOneByAdvertisementId(long advertisementId) {
        log.trace("Started getOneByAdvertisementId(long) method");
        log.debug("Provided parameters advertisementId: {}", advertisementId);

        return advertisementMapper.toResponseDto(findAdvertisementById(advertisementId));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> findAdvertisementsByName(String title,
                                                                      Pageable pageable) {
        log.trace("Started findAdvertisementsByName(String, Pageable) method");
        log.debug("Provided parameters title: {}, pageable: {}", title, pageable);

        Page<Advertisement> page = advertisementRepository.findAllByTitle(title, pageable);

        return advertisementMapper.toPageDto(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> findAdvertisementsByNameAndCategoryId(String title,
                                                                                   Long categoryId,
                                                                                   Pageable pageable) {
        log.trace("Started findAdvertisementsByNameAndCategoryId(String, Long, Pageable) method");
        log.debug("Provided parameters title: {}, categoryId: {},  pageable: {}", title, categoryId, pageable);

        Page<Advertisement> page = advertisementRepository.findAllByTitleAndCategoryId(title, categoryId, pageable);

        return advertisementMapper.toPageDto(page);
    }


    private Advertisement findAdvertisementById(long advertisementId) {
        log.trace("called findByAdvertisementId(long) method");
        log.debug("provided parameter advertisementId: {}", advertisementId);

        return advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> {
                    NotFoundEntityException notFoundEntityException = new NotFoundEntityException();
                    log.error("Advertisement with provided id not found", notFoundEntityException);
                    return notFoundEntityException;
                });
    }

}
