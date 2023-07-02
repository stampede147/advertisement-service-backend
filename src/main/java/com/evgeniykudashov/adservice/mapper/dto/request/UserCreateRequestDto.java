package com.evgeniykudashov.adservice.mapper.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserCreateRequestDto {


    String firstName;
    String lastName;
    LocalDate birthdate;
    String email;
    String username;
    String password;

}
