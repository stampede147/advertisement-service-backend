package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserRequestDto {

    @Pattern(regexp = "[a-zA-z]+", groups = {CreateConstraint.class, UpdateConstraint.class})
    String firstName;
    @Pattern(regexp = "[a-zA-z]+", groups = {CreateConstraint.class, UpdateConstraint.class})
    String lastName;

    @Pattern(regexp = "^[a-zA-Z][a-zA-z0-9]{4,19}$",
            groups = {CreateConstraint.class, UpdateConstraint.class},
            message = "password length should be minimum 4 and maximum 19, and consist of latin letters and digits")
    String username;

//    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{4,}$",
//            groups = {CreateConstraint.class, UpdateConstraint.class},
//            message = "password length should be minimum 4 and consist of latin letters and digits")
    String password;

}
