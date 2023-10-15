package com.evgeniykudashov.adservice.dto.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ChatResponseAdvertisementDto {

    @Parameter(description = "The id of advertisement")
    long id;

    @Parameter(description = "The title of advertisement")
    String title;

    String price;

    List<ImageEntityResponseDto> images;

    UserResponseDto seller;

}
