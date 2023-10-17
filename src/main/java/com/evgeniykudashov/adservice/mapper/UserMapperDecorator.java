package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.controller.rest.StaticFilePathController;
import com.evgeniykudashov.adservice.dto.response.AdvertisementUserResponseDto;
import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.user.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Mapper(componentModel = "spring")
public abstract class UserMapperDecorator extends UserMapper {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(@Qualifier("delegate") UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public AdvertisementUserResponseDto toAdvertisementUserResponseDto(User user) {
        AdvertisementUserResponseDto dto = userMapper.toAdvertisementUserResponseDto(user);

        if (dto.getImage() == null && dto.getFirstName() != null) {

            dto.setImage(mapImageEntityResponseDto(user.getFirstName()));

        }
        return dto;
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(user);

        userResponseDto.setImage(mapImageEntityResponseDto(user.getFirstName()));

        return userResponseDto;
    }

    private ImageEntityResponseDto mapImageEntityResponseDto(String firstName) {
        ImageEntityResponseDto dto = new ImageEntityResponseDto();

        dto.setId(null);

        String link = MvcUriComponentsBuilder.fromMethodName(StaticFilePathController.class,
                        "getUserAvatarStub",
                        resolveDirectoryByFirstName(firstName),
                        getFileName())
                .build()
                .toString();

        dto.setLink(link);

        return dto;
    }

    private String getFileName() {
        return "256x256.png";
    }

    private String resolveDirectoryByFirstName(String name) {
        return name.substring(0, 1).toUpperCase();

    }

}
