package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@RequiredArgsConstructor(onConstructor_ = @Autowired)

@Slf4j
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final UserRepository userRepository;

    private final AdvertisementMapper advertisementMapper;

    private final Converter<Principal, Long> principalConverter;


    @Override
    @Transactional
    public long createAdvertisementByPrincipal(Principal principal, AdvertisementRequestDto dto) {
        log.trace("Started createAdvertisement(Principal, AdvertisementRequestDto) method");
        log.debug("Provided parameter dto: {}, principal : {}", dto, principal);

        Advertisement advertisement = advertisementMapper.toAdvertisement(dto,
                LocalDate.now(),
                AdvertisementStatus.CREATED,
                userRepository.getReferenceById(principalConverter.convert(principal))
        );
        return advertisementRepository.save(advertisement).getId();
    }

    @Override
    @Transactional
    public void removeAdvertisementById(long advertisementId) {
        log.trace("Started method removeAdvertisementById(long)");
        log.debug("Provided parameter advertisementId: {}", advertisementId);

        advertisementRepository.deleteById(advertisementId);
    }


    /**
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
        return advertisementMapper.toPageDto(advertisementRepository.findAllBySellerId(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> getPageByPrincipal(Principal principal, Pageable pageable) {
        log.trace("Started getPageByPrincipal(Principal, Pageable) method");
        log.debug("Provided parameters principal: {}, pageable: {}", principal, pageable);

        Page<Advertisement> advertisements =
                advertisementRepository.findAllBySellerId(principalConverter.convert(principal), pageable);
        return advertisementMapper.toPageDto(advertisements);
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementResponseDto getOneByAdvertisementId(long advertisementId) {
        log.trace("Started getOneByAdvertisementId(long) method");
        log.debug("Provided parameters advertisementId: {}", advertisementId);


        return advertisementMapper.toResponseDto(findByAdvertisementId(advertisementId));
    }

    private Advertisement findByAdvertisementId(long advertisementId) {
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
