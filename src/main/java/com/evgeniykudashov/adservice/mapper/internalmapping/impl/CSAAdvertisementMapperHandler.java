package com.evgeniykudashov.adservice.mapper.internalmapping.impl;

import com.evgeniykudashov.adservice.dto.request.CSAAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.CSAAdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandlerBeanNameConstants;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.CSAAdvertisement;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Component(value = AdvertisementMapperHandlerBeanNameConstants.CSA_ADVERTISEMENT_MAPPER_HANDLER)
public class CSAAdvertisementMapperHandler
        extends GenericAdvertisementMapperHandler<CSAAdvertisement, CSAAdvertisementRequestDto, CSAAdvertisementResponseDto> {

    @Autowired
    public CSAAdvertisementMapperHandler(@Qualifier(AdvertisementMapperHandlerBeanNameConstants.BASE_ADVERTISEMENT_MAPPER_HANDLER)
                                         AdvertisementMapperHandler parent) {
        super(parent);
    }

    public void map(CSAAdvertisement target, CSAAdvertisementRequestDto source) {

        target.setHealthCondition(source.healthCondition);
        target.setBrand(source.getBrand());
        target.setColor(source.getColor());
    }

    @Override
    public void map(CSAAdvertisementResponseDto target, CSAAdvertisement source) {

        target.setHealthCondition(source.getHealthCondition());
        target.setBrand(source.getBrand());
        target.setColor(source.getColor());

    }

}
