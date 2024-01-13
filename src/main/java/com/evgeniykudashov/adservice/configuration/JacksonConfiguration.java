package com.evgeniykudashov.adservice.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JacksonConfiguration {

    @Autowired
    public void mapperConfiguration(ObjectMapper mapper) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new JavaTimeModule());


    }

    @Autowired
    public void registerJsonTypeNameAnnotationClassInheritors(ObjectMapper mapper) {

        List<Class<?>> classes = ClassUtil.findClassesWithAnnotation("com.evgeniykudashov.adservice.dto.request", JsonTypeName.class);
        mapper.registerSubtypes(classes);
    }


}
