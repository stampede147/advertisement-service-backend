package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.model.advertisement.ClothingType;
import com.evgeniykudashov.adservice.model.advertisement.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothingAdvertisementResponseDto extends AdvertisementResponseDto {

    private String brand;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String color;

    private int size;
    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

}
