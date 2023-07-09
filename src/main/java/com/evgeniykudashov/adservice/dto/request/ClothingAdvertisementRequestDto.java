package com.evgeniykudashov.adservice.dto.request;


import com.evgeniykudashov.adservice.model.advertisement.ClothingType;
import com.evgeniykudashov.adservice.model.advertisement.Gender;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothingAdvertisementRequestDto extends AdvertisementRequestDto {

    @Pattern(regexp = "%[A-Za-z]+$", groups = {CreateConstraint.class, UpdateConstraint.class})
    private String brand;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Pattern(regexp = "^[A-Za-z]+$", groups = {CreateConstraint.class, UpdateConstraint.class})
    private String color;

    @PositiveOrZero(groups = {CreateConstraint.class, UpdateConstraint.class})
    private int size;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;
}
