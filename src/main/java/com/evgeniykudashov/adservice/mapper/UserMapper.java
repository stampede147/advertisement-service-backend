package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ImageEntityMapper.class)
@Setter(onMethod_ = @Autowired)
public abstract class UserMapper {


    //    @Mapping(target = "password", source = "encodedPassword")
//    @Mapping(target = "role", source = "role")
//    @Mapping(target = "imageEntity", source = "image")
    @Mapping(target = "id", ignore = true)
    public abstract User toUser(UserRequestDto dto);


    @Mapping(target = "image", source = "imageEntity")
    public abstract UserResponseDto toUserResponseDto(User user);
}
