package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;


public interface AdvertisementMapperContext {

    AdvertisementMapper getMapper(AdvertisementType type);
}
