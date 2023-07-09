package com.evgeniykudashov.adservice.configuration;

import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.ClothingAdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.DefaultAdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.ShoeAdvertisementMapper;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MapperConfiguration {

    @Bean
    public Map<AdvertisementType, AdvertisementMapper> advertisementMapperMap(ApplicationContext ctxt) {
        Map<AdvertisementType, AdvertisementMapper> map = new HashMap<>();
        map.put(AdvertisementType.DEFAULT, ctxt.getBean(DefaultAdvertisementMapper.class));
        map.put(AdvertisementType.CLOTHING, ctxt.getBean(ClothingAdvertisementMapper.class));
        map.put(AdvertisementType.SHOE, ctxt.getBean(ShoeAdvertisementMapper.class));
        return map;
    }


}
