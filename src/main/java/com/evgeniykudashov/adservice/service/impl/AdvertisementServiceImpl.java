package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperContext;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementMapperContext context;

    @Override
    @Transactional
    public long createAdvertisement(AdvertisementRequestDto dto) {
        AdvertisementMapper mapper = context.getMapper(dto.getType());

        return advertisementRepository.save(mapper.toAdvertisement(dto))
                .getId();
    }

    @Transactional
    public void removeAdvertisementById(long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    public PageDto<? extends AdvertisementResponseDto> getPageByUserId(long userId, Pageable pageable) {
        return context.getMapper(AdvertisementType.DEFAULT)
                .toPageDto(advertisementRepository.findAllByOwnerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementResponseDto getOneByAdvertisementId(long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(NotFoundEntityException::new);

        return context.getMapper(advertisement.getType())
                .toResponseDto(advertisement);
    }

}
