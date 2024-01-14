package com.evgeniykudashov.adservice.mapper.internalmapping.impl;

import com.evgeniykudashov.adservice.dto.request.ShoeAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.ShoeAdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandlerBeanNameConstants;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.ShoeAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = AdvertisementMapperHandlerBeanNameConstants.SHOE_ADVERTISEMENT_MAPPER_HANDLER)
public class ShoeAdvertisementMapperHandler extends GenericAdvertisementMapperHandler<ShoeAdvertisement, ShoeAdvertisementRequestDto, ShoeAdvertisementResponseDto> {

    @Autowired
    public ShoeAdvertisementMapperHandler(@Qualifier(value = AdvertisementMapperHandlerBeanNameConstants.CSA_ADVERTISEMENT_MAPPER_HANDLER) AdvertisementMapperHandler parent) {
        super(parent);
    }

    @Override
    protected void map(ShoeAdvertisement target, ShoeAdvertisementRequestDto source) {
        target.setSize(source.getSize());
    }

    @Override
    protected void map(ShoeAdvertisementResponseDto target, ShoeAdvertisement source) {

        target.setSize(source.getSize());
    }

}
