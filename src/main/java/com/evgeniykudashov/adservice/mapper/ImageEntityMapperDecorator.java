package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.controller.rest.ImageController;
import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;


@Mapper(componentModel = "spring")
public abstract class ImageEntityMapperDecorator extends ImageEntityMapper {

    private ImageEntityMapper imageEntityMapper;

    @Autowired
    public void setImageEntityMapper(@Qualifier("delegate") ImageEntityMapper imageEntityMapper) {
        this.imageEntityMapper = imageEntityMapper;
    }

    @Override
    public ImageEntityResponseDto toResponseDto(ImageEntity imageEntity) {
        if (imageEntity == null) {
            return null;
        }

        ImageEntityResponseDto imageEntityResponseDto = imageEntityMapper.toResponseDto(imageEntity);

        UriComponentsBuilder builder = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getImage", imageEntity.getId());

        imageEntityResponseDto.setLink(builder.build().toString());

        return imageEntityResponseDto;
    }
}
