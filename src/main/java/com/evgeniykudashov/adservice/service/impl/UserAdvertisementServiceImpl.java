package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.event.ViewAdvertisementEvent;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.repository.*;
import com.evgeniykudashov.adservice.service.AuthorizedUserAdvertisementService;
import com.evgeniykudashov.adservice.service.factory.AdvertisementFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAdvertisementServiceImpl implements AuthorizedUserAdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    private final ViewedAdvertisementRepository viewedAdvertisementRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ImageEntityRepository imageEntityRepository;

    private final AdvertisementMapper advertisementMapper;

    private final Converter<Principal, Long> principalConverter;

    private final AdvertisementFactory factory;

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public long createAdvertisementByPrincipal(Principal principal, AdvertisementRequestDto dto) {
        log.trace("Started createAdvertisement(Principal, AdvertisementRequestDto) method");
        log.debug("Provided parameter dto: {}, principal : {}", dto, principal);

        Advertisement advertisement = factory.createAdvertisement(() -> advertisementMapper.toAdvertisement(dto),
                () -> userRepository.getReferenceById(principalConverter.convert(principal)),
                () -> categoryRepository.getReferenceById(dto.getCategoryId()),
                () -> dto.getImages().stream().map(imageEntityRepository::getReferenceById).collect(Collectors.toList()));


        return advertisementRepository.save(advertisement).getId();
    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public void hideAdvertisementById(Principal principal, long id) {
        log.trace("Started hideAdvertisementById(long) method");
        log.debug("Provided parameters id: {}", id);

        this.findByAdvertisementIdAndSellerId(id, principalConverter.convert(principal))
                .setStatus(AdvertisementStatus.HIDDEN);

    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public void removeAdvertisementById(Principal principal, long advertisementId) {
        log.trace("Started method removeAdvertisementById(long)");
        log.debug("Provided parameter advertisementId: {}", advertisementId);

        Advertisement advertisement = this.findByAdvertisementIdAndSellerId(advertisementId, principalConverter.convert(principal));

        advertisementRepository.delete(advertisement);
    }


    @Override
    @Transactional(readOnly = true)
    public PageDto<AdvertisementResponseDto> getAdvertisementPage(Principal principal, Pageable pageable) {
        log.trace("Started getPageByPrincipal(Principal, Pageable) method");
        log.debug("Provided parameters principal: {}, pageable: {}", principal, pageable);

        Page<Advertisement> advertisements = advertisementRepository.findAdvertisementsBySellerId(principalConverter.convert(principal), pageable);

        return advertisementMapper.toPageDto(advertisements);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("isFullyAuthenticated()")
    public AdvertisementResponseDto getAdvertisementById(Principal principal, long advertisementId) {
        log.trace("Started getOneByAdvertisementId(long) method");
        log.debug("Provided parameters advertisementId: {}", advertisementId);

        return advertisementMapper.toResponseDto(this.findByAdvertisementIdAndSellerId(advertisementId, principalConverter.convert(principal)));
    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public void activateAdvertisementById(Principal principal, long id) {
        log.trace("Started activeAdvertisementById(long) method");
        log.debug("Provided parameters id: {}", id);

        this.findByAdvertisementIdAndSellerId(id, principalConverter.convert(principal))
                .setStatus(AdvertisementStatus.ACTIVE);
    }

    @Override
    public void addViewedAdvertisementById(Principal principal, long advertisementId) {

        Long userId = principalConverter.convert(principal);
        long itemId = advertisementId;

        ViewedAdvertisement viewedAdvertisement = new ViewedAdvertisement();
        viewedAdvertisement.setAdvertisement(advertisementRepository.getReferenceById(itemId));
        viewedAdvertisement.setUser(userRepository.getReferenceById(userId));

        viewedAdvertisementRepository.save(viewedAdvertisement);
    }

    @EventListener(condition = "#event.principal != null")
    public void addViewedAdvertisementByEvent(ViewAdvertisementEvent event) {
        log.info("called addViewedAdvertisementByEvent(ViewAdvertisementEvent) method");
        log.debug("Provided parameters event: {}", event);

        this.addViewedAdvertisementById(event.getPrincipal(), event.getAdvertisementId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvertisementResponseDto> getViewedAdvertisements(Principal principal, Pageable pageable) {
        Long userId = principalConverter.convert(principal);

        List<Advertisement> viewedAdvertisements = advertisementRepository.findViewedAdvertisementsByUserId(userId, pageable);

        return advertisementMapper.toResponseDto(viewedAdvertisements);
    }

    private Advertisement findByAdvertisementIdAndSellerId(long advertisementId, long sellerId) {
        log.trace("called findByAdvertisementId(long) method");
        log.debug("provided parameter advertisementId: {}", advertisementId);

        return advertisementRepository.findByAdvertisementIdAndSellerId(advertisementId, sellerId)
                .orElseThrow(() -> {
                    NotFoundEntityException notFoundEntityException = new NotFoundEntityException();
                    log.error("Advertisement with provided id not found", notFoundEntityException);
                    return notFoundEntityException;
                });
    }

}
