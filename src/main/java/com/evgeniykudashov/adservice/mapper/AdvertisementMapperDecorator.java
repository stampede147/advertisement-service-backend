package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.controller.rest.ImageController;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


//@Mapper(componentModel = "spring", uses = ImageEntityMapperDecorator.class)
public abstract class AdvertisementMapperDecorator extends AdvertisementMapper {

    private AdvertisementMapper advertisementMapper;

    @Autowired
    public void setAdvertisementMapper(@Qualifier("delegate") AdvertisementMapper advertisementMapper) {
        this.advertisementMapper = advertisementMapper;
    }

    @Override
    public AdvertisementResponseDto toResponseDto(Advertisement advertisement) {
        AdvertisementResponseDto responseDto = advertisementMapper.toResponseDto(advertisement);

        addLinkToResponseDtoImages(responseDto);

        return responseDto;

    }

    private void addLinkToResponseDtoImages(AdvertisementResponseDto responseDto) {
        responseDto.getImages()
                .replaceAll(image -> {
                    image.setLink(MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getImage", image.getId())
                            .build()
                            .toString());
                    return image;
                });
    }

}
