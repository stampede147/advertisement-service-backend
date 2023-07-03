package com.evgeniykudashov.adservice.mapper.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserRequestDto {

    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "advertisement id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "advertisement id should be positive")
    })
    long userId;

    @Pattern(regexp = "[a-zA-z]+", groups = {CreateConstraint.class, UpdateConstraint.class})
    String firstName;
    @Pattern(regexp = "[a-zA-z]+", groups = {CreateConstraint.class, UpdateConstraint.class})
    String lastName;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    LocalDate birthdate;

    @Pattern(regexp = "\\w+@\\w+\\.[a-zA-Z]+$", groups = {CreateConstraint.class, UpdateConstraint.class})
    String email;

    @Pattern(regexp = "^[a-zA-Z][a-zA-z0-9]{4,19}$",
            groups = {CreateConstraint.class, UpdateConstraint.class},
            message = "password length should be minimum 4 and maximum 19, and consist of latin letters and digits")
    String username;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{4,}$",
            groups = {CreateConstraint.class, UpdateConstraint.class},
            message = "password length should be minimum 4 and consist of latin letters and digits")
    String password;

}
