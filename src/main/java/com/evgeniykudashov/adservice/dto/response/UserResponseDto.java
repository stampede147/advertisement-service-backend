package com.evgeniykudashov.adservice.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserResponseDto {

    public long id;

    public String firstName;

    public String lastName;

    public LocalDate birthdate;

    public String email;
}
