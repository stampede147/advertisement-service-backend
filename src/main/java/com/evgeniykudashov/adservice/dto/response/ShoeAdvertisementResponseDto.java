package com.evgeniykudashov.adservice.dto.response;


import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.model.advertisement.Gender;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = AdvertisementType.Constants.SHOE, description = "The schema for shoe advertisement. It used as response from server")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoeAdvertisementResponseDto extends AdvertisementResponseDto {


    private String brand;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Parameter(description = "the color of shoe")
    private String color;

    @Parameter(description = "the size of shoe")
    private String size;

}
