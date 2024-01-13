package com.evgeniykudashov.adservice.mapper.internalmapping.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Primary
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class AdvertisementMapperHandlerDelegator implements AdvertisementMapperHandler {

    private final Map<AdvertisementType,
            GenericAdvertisementMapperHandler<? extends Advertisement, ? extends AdvertisementRequestDto, ? extends AdvertisementResponseDto>> delegates;

    @Override
    public void toEntity(Advertisement a, AdvertisementRequestDto rtd) {
        log.trace("calling method toEntity(Advertisement, AdvertisementRequestDto)");
        log.debug("provided value [Advertisement] - {}", a);
        log.debug("provided value [AdvertisementRequestDto] - {}", rtd);

        delegates.get(a.getType())
                .toEntity(a, rtd);
    }

    @Override
    public void toDto(AdvertisementResponseDto red, Advertisement a) {
        log.trace("calling method toDto(AdvertisementResponseDto, Advertisement)");
        log.debug("provided value [Advertisement] - {}", a);
        log.debug("provided value [AdvertisementResponseDto] - {}", red);

        delegates.get(a.getType())
                .toDto(red, a);
    }
}
