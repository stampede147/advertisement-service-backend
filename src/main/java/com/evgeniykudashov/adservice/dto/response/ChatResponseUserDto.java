package com.evgeniykudashov.adservice.dto.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ChatResponseUserDto {

    @Parameter(description = "the ID of user-participant")
    long id;

    String firstName;

    String lastName;
}
