package com.evgeniykudashov.adservice.mapper.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserResponseDto {

    String firstName;

    String lastName;

    String createdAt;
}
