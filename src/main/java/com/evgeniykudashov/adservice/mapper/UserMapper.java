package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.request.UserCreateRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class UserMapper {


    public abstract User toUser(UserCreateRequestDto dto);

    public abstract UserResponseDto toUserResponseDto(User user);
}
