package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementUserResponseDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.mapper.decorator.UserMapperDecorator;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.ImageEntityRepository;
import com.evgeniykudashov.adservice.service.factory.UserFactory;
import lombok.Setter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = {ImageEntityMapper.class, UserFactory.class},
        builder = @Builder(disableBuilder = true))
@Setter(onMethod_ = @Autowired)
@DecoratedWith(UserMapperDecorator.class)
public abstract class UserMapper {

    protected UserFactory userFactory;

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract User toUser(UserRequestDto dto, ImageEntityRepository imageEntityRepository);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    protected abstract User mapUser(@MappingTarget User user, UserRequestDto dto);

    @Mapping(target = "image", source = "image")
    public abstract UserResponseDto toUserResponseDto(User user);

    public abstract AdvertisementUserResponseDto toAdvertisementUserResponseDto(User user);

    @ObjectFactory
    protected User createUser(UserRequestDto dto) {

        return dto == null ? null : userFactory.createUser(dto.getPassword());
    }
}
