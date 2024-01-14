package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;

import java.security.Principal;

public interface AdvertisementFactory {

    Advertisement createAdvertisementForType(AdvertisementType type, Principal principal);

    AdvertisementResponseDto createAdvertisementResponseDtoForType(AdvertisementType type);
}
