package com.evgeniykudashov.adservice.mapper.internalmapping.impl;

import com.evgeniykudashov.adservice.dto.request.ClothingAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.ClothingAdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandlerBeanNameConstants;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.ClothingAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = AdvertisementMapperHandlerBeanNameConstants.CLOTHING_ADVERTISEMENT_MAPPER_HANDLER)
public class ClothingAdvertisementMapperHandler
        extends GenericAdvertisementMapperHandler<ClothingAdvertisement, ClothingAdvertisementRequestDto, ClothingAdvertisementResponseDto> {

    @Autowired
    public ClothingAdvertisementMapperHandler(@Qualifier(AdvertisementMapperHandlerBeanNameConstants.CSA_ADVERTISEMENT_MAPPER_HANDLER)
                                              AdvertisementMapperHandler parent) {
        super(parent);
    }

    @Override
    protected void map(ClothingAdvertisement target, ClothingAdvertisementRequestDto source) {
        target.setSize(source.getSize());
    }

    @Override
    protected void map(ClothingAdvertisementResponseDto target, ClothingAdvertisement source) {
        target.setSize(source.getSize());
    }

}
