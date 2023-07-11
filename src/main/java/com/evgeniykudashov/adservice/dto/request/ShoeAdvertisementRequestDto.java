package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.Gender;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoeAdvertisementRequestDto extends AdvertisementRequestDto {

    @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateConstraint.class, UpdateConstraint.class})
    private String brand;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    private Gender gender;

    //    @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateConstraint.class, UpdateConstraint.class})
    private String color;

    //    @PositiveOrZero(groups = {CreateConstraint.class, UpdateConstraint.class})
    @JsonUnwrapped
    private ShoeSizeDto size;
}
