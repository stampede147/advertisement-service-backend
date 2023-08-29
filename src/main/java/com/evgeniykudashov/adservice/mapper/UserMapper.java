package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class UserMapper {


    @Mapping(target = "birthdate", expression = "java(dto.getBirthdate())")
    @Mapping(target = "password", expression = "java(password)")
    @Mapping(target = "role", expression = "java(role)")
    public abstract User toUser(UserRequestDto dto,
                                String password,
                                Role role);

    public abstract UserResponseDto toUserResponseDto(User user);
}
