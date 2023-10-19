package com.evgeniykudashov.adservice.mapper.decorator;

import com.evgeniykudashov.adservice.controller.rest.ImageController;
import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.mapper.ImageEntityMapper;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


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

        String link = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getImage", imageEntity.getId())
                .build().toString();

        imageEntityResponseDto.setLink(link);

        return imageEntityResponseDto;
    }
}
