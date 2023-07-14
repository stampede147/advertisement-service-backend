package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum AdvertisementMapperType {

    DEFAULT(null),
    SHOE(AdvertisementType.SHOE),
    CLOTHING(AdvertisementType.CLOTHING);


    AdvertisementType advertisementType;

    AdvertisementMapperType(AdvertisementType advertisementType) {
        this.advertisementType = advertisementType;
    }

    public static AdvertisementMapperType fromAdvertisementType(AdvertisementType type) {
        return Arrays.stream(AdvertisementMapperType.values())
                .filter(mapperType -> mapperType.supports(type))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("not found such advertisement mapper type from Advertisement type: " + type.name());
                });
    }

    private boolean supports(AdvertisementType type) {
        return this.advertisementType != null && advertisementType.equals(type);
    }

}
