package com.evgeniykudashov.adservice.configuration;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.internalmapping.EntityMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GenericInheritanceConfig {

    @Autowired
    ApplicationContext ctxt;

    private static final TypeVariable getTypeVariableWithName(Map<TypeVariable, Type> map, String typeVariableName) {
        return map.entrySet()
                .stream()
                .filter(typ -> typ.getKey().getName().equals(typeVariableName))
                .findFirst().map(Map.Entry::getKey).orElse(null);

    }

    private static final Class getClassByTypeVariableName(Map<TypeVariable, Type> map, String typeVariableName) {
        return (Class) map.get(getTypeVariableWithName(map, typeVariableName));
    }
    //

    //
    Map<AdvertisementType, Class<? extends Advertisement>> advertisementTypeAdvertisementAssociation() {

        return ClassUtil.findClassesWithAnnotation(Advertisement.class.getPackageName(), DiscriminatorValue.class)
                .stream()
                .collect(Collectors.toMap(
                        clazz -> AdvertisementType.Utility.valueForTitle(clazz.getAnnotation(DiscriminatorValue.class).value()),
                        clazz -> (Class<? extends Advertisement>) clazz));
    }

    @Bean
    Map<Class<? extends Advertisement>, AdvertisementType> advertisementAdvertisementTypeAssociation() {
        return advertisementTypeAdvertisementAssociation()
                .entrySet()
                .stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    //
    @Bean
    Map<Class<? extends GenericAdvertisementMapperHandler<? extends Advertisement, ? extends AdvertisementRequestDto, ? extends AdvertisementResponseDto>>,
            Class<? extends Advertisement>> handlerAdvertisementAssociation(List<EntityMappingDefinition> definitions) {

        return (Map) definitions.stream()
                .collect(Collectors.toMap(EntityMappingDefinition::getHandlerClass, EntityMappingDefinition::getEntityClass));
    }

    @Bean
    Map<AdvertisementType,
            GenericAdvertisementMapperHandler<? extends Advertisement, ? extends AdvertisementRequestDto, ? extends AdvertisementResponseDto>> mapAdvertisementTypeHandlers(List<EntityMappingDefinition> definitions,
                                                                                                                                                                            Map<Class<? extends Advertisement>, AdvertisementType> types) {
        return (Map) definitions.stream()
                .filter(definition -> types.containsKey(definition.getEntityClass()))
                .collect(Collectors.toMap(k -> types.get(k.getEntityClass()), k -> ctxt.getBean(k.getHandlerClass())));
    }

    @Bean
    Map<AdvertisementType,
            Class<? extends AdvertisementRequestDto>> advertisementTypeAdvertisementRequestDtoAssociation(List<EntityMappingDefinition> definitions,
                                                                                                          Map<Class<? extends Advertisement>, AdvertisementType> types) {

        return definitions.stream()
                .filter(definition -> types.containsKey(definition.getEntityClass()) && definition.getRequestClass().isAssignableFrom(AdvertisementRequestDto.class))
                .collect(Collectors.toMap(def -> types.get(def.getEntityClass()), def -> (Class<? extends AdvertisementRequestDto>) def.getRequestClass()));

    }

    @Bean
    Map<AdvertisementType,
            Class<? extends AdvertisementResponseDto>> advertisementTypeAdvertisementResponseDtoAssociation(List<EntityMappingDefinition> definitions,
                                                                                                            Map<Class<? extends Advertisement>, AdvertisementType> types) {

        definitions.forEach(System.out::println);
        Map<AdvertisementType, Class<? extends AdvertisementResponseDto>> collect = definitions.stream()
                .filter(definition -> types.containsKey(definition.getEntityClass()))
                .collect(Collectors.toMap(def -> types.get(def.getEntityClass()), def -> (Class<? extends AdvertisementResponseDto>) def.getResponseClass()));
        System.out.println(collect);
        return collect;

    }

    @Bean
    List<EntityMappingDefinition> inheritanceDefinitionList() {

        List<EntityMappingDefinition> collect = ClassUtil.findClassesInheritors(GenericAdvertisementMapperHandler.class.getPackageName(), GenericAdvertisementMapperHandler.class)
                .stream()
                .map(k -> {
                    Map<TypeVariable, Type> map = GenericTypeResolver.getTypeVariableMap(k);
                    return new EntityMappingDefinitionImpl((Class<EntityMapperHandler>) k, getClassByTypeVariableName(map, "A"), getClassByTypeVariableName(map, "RTD"), getClassByTypeVariableName(map, "RED"));
                })
                .collect(Collectors.toList());


        return collect;
    }


    interface EntityMappingDefinition {

        Class<EntityMapperHandler> getHandlerClass();

        Class<?> getEntityClass();

        Class<?> getRequestClass();

        Class<?> getResponseClass();

    }

    @AllArgsConstructor
    @ToString
    static class EntityMappingDefinitionImpl
            implements EntityMappingDefinition {

        private final Class<EntityMapperHandler> handlerClass;
        private final Class<?> entityClass;
        private final Class<?> requestClass;
        private final Class<?> responseClass;

        @Override
        public Class<EntityMapperHandler> getHandlerClass() {
            return handlerClass;
        }

        @Override
        public Class<?> getEntityClass() {
            return entityClass;
        }

        @Override
        public Class<?> getRequestClass() {
            return requestClass;
        }

        @Override
        public Class<?> getResponseClass() {
            return responseClass;
        }
    }

}
