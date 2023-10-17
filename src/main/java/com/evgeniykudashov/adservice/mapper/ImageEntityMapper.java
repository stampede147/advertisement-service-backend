package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.mapper.decorator.ImageEntityMapperDecorator;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@DecoratedWith(ImageEntityMapperDecorator.class)
@Mapper(componentModel = "spring")
public abstract class ImageEntityMapper {

    public abstract ImageEntityResponseDto toResponseDto(ImageEntity imageEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "location", source = "location")
    public abstract ImageEntity toImageEntity(String id, String location);
}
