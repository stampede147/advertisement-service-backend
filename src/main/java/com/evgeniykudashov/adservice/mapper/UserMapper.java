package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class UserMapper {


    @Mapping(target = "birthdate", source = "birthdate")

    public abstract User toUser(UserRequestDto dto);

    @Mapping(target = "userId", source = "id")
    public abstract UserResponseDto toUserResponseDto(User user);
}
