package com.evgeniykudashov.adservice.dto.response;


import com.evgeniykudashov.adservice.model.advertisement.AdvertisementLocation;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.boot.model.process.internal.UserTypeResolution;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdvertisementResponseDto {

    long id;

    String title;

    String description;

    @Parameter(description = "the price of advertisement")
    long price;

    @Parameter(description = "the status of advertisement")
    @Enumerated(value = EnumType.STRING)
    AdvertisementStatus status;

    LocationResponseDto location;

    LocalDate startTime;

    UserResponseDto seller;

}
