package com.evgeniykudashov.adservice.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AdvertisementRequestDto {

    @Size(min = 1, max = 255)
    String title;

    @NonNull
    Double price;

    @Parameter(description = "the description of advertisement")
    @Size(min = 1,  max = 512)
    String description;

    @NotNull
    LocationRequestDto location;
}
