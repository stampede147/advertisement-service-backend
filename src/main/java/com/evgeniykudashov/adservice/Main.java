package com.evgeniykudashov.adservice;

import com.evgeniykudashov.adservice.service.AdvertisementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
})
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        AdvertisementService bean = run.getBean(AdvertisementService.class);
//
//        AdvertisementRequestDto dto = new AdvertisementRequestDto();
//        AdvertisementRequestDto dto1 = new ClothingAdvertisementRequestDto();
//
//        List<Class<?>> classes = new ArrayList<>();
//        classes.add(dto.getClass());
//        classes.add(dto1.getClass());
//
//        System.out.println(classes);
    }

    @Autowired
    public void mapperConfiguration(ObjectMapper mapper) {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new JavaTimeModule());
    }

}
