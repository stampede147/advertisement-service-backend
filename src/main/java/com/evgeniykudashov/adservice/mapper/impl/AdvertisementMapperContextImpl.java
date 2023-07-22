package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperContext;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;


@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementMapperContextImpl implements AdvertisementMapperContext {

    private final Map<AdvertisementType, AdvertisementMapper> mappers;

    @Override
    public AdvertisementMapper getMapper(AdvertisementType type) throws NoSuchElementException {
        return mappers.computeIfAbsent(type, (typ) -> {
            AdvertisementMapper defaultAdvertisementMapper = getDefaultMapper();
            log.warn("Not found mapper for provided advertisementType: {}. Returned default mapper: {}", typ, defaultAdvertisementMapper.getClass());
            return defaultAdvertisementMapper;
        });
    }

    private AdvertisementMapper getDefaultMapper() {
        return mappers.computeIfAbsent(AdvertisementType.DEFAULT, (typ1) -> {
            RuntimeException exception = new RuntimeException();
            log.error("Not found default mapper with advertisementType: {}" + AdvertisementType.DEFAULT, exception);
            throw exception;
        });
    }

}
