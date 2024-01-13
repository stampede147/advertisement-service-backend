package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

@Component
public class AdvertisementFactoryImpl implements AdvertisementFactory {

    private final Map<AdvertisementType, Class<? extends Advertisement>> advertisementMap;

    private final Map<AdvertisementType, Class<? extends AdvertisementResponseDto>> advertisementResponseDtoMap;

    private final Converter<Principal, User> principalConverter;


    @Autowired
    public AdvertisementFactoryImpl(Map<AdvertisementType, Class<? extends Advertisement>> advertisementMap,
                                    Map<AdvertisementType, Class<? extends AdvertisementResponseDto>> advertisementResponseDtoMap,
                                    Converter<Principal, User> principalConverter) {
        this.advertisementMap = advertisementMap;
        this.advertisementResponseDtoMap = advertisementResponseDtoMap;
        this.principalConverter = principalConverter;
    }

    @Override
    public Advertisement createAdvertisementForType(AdvertisementType type, Principal principal) {

        Advertisement advertisement = null;

        try {
            advertisement = advertisementMap.get(type)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            throw new RuntimeException("problem with creating new instance", e);
        }

        advertisement.setStatus(AdvertisementStatus.CREATED);

        advertisement.setStartTime(LocalDate.now());

        advertisement.setSeller(principalConverter.convert(principal));

        return advertisement;
    }

    @Override
    public AdvertisementResponseDto createAdvertisementResponseDtoForType(AdvertisementType type) {

        try {
            return advertisementResponseDtoMap.get(type)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            throw new RuntimeException("problem with creating new instance", e);
        }
    }

}