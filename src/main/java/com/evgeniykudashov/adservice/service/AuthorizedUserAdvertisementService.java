package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

/**
 * The service provides functionality to the Authorized user
 */
public interface AuthorizedUserAdvertisementService {

    long createAdvertisementByPrincipal(Principal principal, AdvertisementRequestDto dto);

    void removeAdvertisementById(Principal principal, long advertisementId);

    PageDto<AdvertisementResponseDto> getAdvertisementPage(Principal principal, Pageable pageable);

    AdvertisementResponseDto getAdvertisementById(Principal principal, long advertisementId);

    void activateAdvertisementById(Principal principal, long id);

    void hideAdvertisementById(Principal principal, long id);

    void addViewedAdvertisementById(Principal principal, long advertisementId);

    List<AdvertisementResponseDto> getViewedAdvertisements(Principal principal, Pageable pageable);


}
