package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AdvertisementRequestDto {

    @Size(min = 1, max = 255, groups = {CreateConstraint.class})
    String title;

    @PositiveOrZero(groups = {CreateConstraint.class})
    Double price;

    @Parameter(description = "the description of advertisement")
    @Size(min = 1, max = 512, groups = {CreateConstraint.class})
    String description;

    @NotNull(groups = {CreateConstraint.class})
    @JsonUnwrapped
    @Valid
    LocationRequestDto location;

    @PositiveOrZero(groups = {CreateConstraint.class})
    Long categoryId;

    @NotEmpty(groups = {CreateConstraint.class})
    List<String> images;
}
