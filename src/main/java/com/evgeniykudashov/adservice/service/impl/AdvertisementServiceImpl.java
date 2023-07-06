package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor(onConstructor_ = @Autowired)

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    private AdvertisementMapper dtoEntityMapper;

    @Override
    @Transactional
    public long create(AdvertisementRequestDto dto) {
        return advertisementRepository.save(dtoEntityMapper.toAdvertisement(dto))
                .getId();
    }

    @Transactional
    public void remove(long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> findAllByUserId(long userId, Pageable pageable) {
        return dtoEntityMapper.toPageDto(advertisementRepository.findAllByOwnerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementResponseDto findById(long advertisementId) {
        return dtoEntityMapper.toAdvertisementResponseDto(advertisementRepository.findById(advertisementId)
                .orElseThrow(NotFoundEntityException::new));
    }

}
