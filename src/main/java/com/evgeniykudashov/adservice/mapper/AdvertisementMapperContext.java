package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.mapper.impl.AdvertisementMapperType;


public interface AdvertisementMapperContext {

    AdvertisementMapper getMapper(AdvertisementMapperType type);
}
