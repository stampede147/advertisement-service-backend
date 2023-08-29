package com.evgeniykudashov.adservice.mapper.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperProxy;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;


@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementMapperProxyImpl implements AdvertisementMapperProxy {

    private final Map<AdvertisementType, AdvertisementMapper> mappers;

    private AdvertisementMapper getMapper(AdvertisementType type) throws NoSuchElementException {
        return mappers.computeIfAbsent(type, (typ) -> {
            AdvertisementMapper defaultAdvertisementMapper = getDefaultMapper();
            log.warn("Not found mapper for provided advertisementType: {}. Returned default mapper: {}", typ, defaultAdvertisementMapper.getClass());
            return defaultAdvertisementMapper;
        });
    }

    protected AdvertisementMapper getDefaultMapper() {
        return mappers.computeIfAbsent(AdvertisementType.DEFAULT, (typ1) -> {
            RuntimeException exception = new RuntimeException();
            log.error("Not found default mapper with advertisementType: {}", AdvertisementType.DEFAULT, exception);
            throw exception;
        });
    }

    @Override
    public Advertisement toAdvertisement(AdvertisementRequestDto dto) {
        return getMapper(dto.getType())
                .toAdvertisement(dto);
    }

    @Override
    public AdvertisementResponseDto toResponseDto(Advertisement advertisement) {
        return getMapper(advertisement.getType())
                .toResponseDto(advertisement);
    }

    @Override
    public PageDto<? extends AdvertisementResponseDto> toPageDto(Page<? extends Advertisement> page) {
        AdvertisementMapper mapper = getDefaultMapper();
        return mapper.toPageDto(page);
    }
}
