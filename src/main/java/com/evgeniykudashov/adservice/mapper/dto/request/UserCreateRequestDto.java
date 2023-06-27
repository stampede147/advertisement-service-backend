package com.evgeniykudashov.adservice.mapper.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserCreateRequestDto {


    String firstName;
    String lastName;
    String birthDate;
    String email;
    String username;
    String password;

}
