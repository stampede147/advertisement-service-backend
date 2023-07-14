package com.evgeniykudashov.adservice.configuration;

import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.AdvertisementMapperType;
import com.evgeniykudashov.adservice.mapper.impl.ClothingAdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.DefaultAdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.impl.ShoeAdvertisementMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MapperConfiguration {

    @Bean
    public Map<AdvertisementMapperType, AdvertisementMapper> advertisementMapperMap(ApplicationContext ctxt) {
        Map<AdvertisementMapperType, AdvertisementMapper> map = new HashMap<>();
        map.put(AdvertisementMapperType.DEFAULT, ctxt.getBean(DefaultAdvertisementMapper.class));
        map.put(AdvertisementMapperType.CLOTHING, ctxt.getBean(ClothingAdvertisementMapper.class));
        map.put(AdvertisementMapperType.SHOE, ctxt.getBean(ShoeAdvertisementMapper.class));
        return map;
    }


}
