package com.evgeniykudashov.adservice.dto.response;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestAddressDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Schema(requiredProperties = Advertisement.DISCRIMINATOR_COLUMN,
        discriminatorProperty = Advertisement.DISCRIMINATOR_COLUMN,
        discriminatorMapping = {
                @DiscriminatorMapping(value = AdvertisementType.Constants.CLOTHING, schema = ClothingAdvertisementResponseDto.class),
                @DiscriminatorMapping(value = AdvertisementType.Constants.SHOE, schema = ShoeAdvertisementResponseDto.class)
        })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = Advertisement.DISCRIMINATOR_COLUMN,
        visible = true)
//        defaultImpl = AdvertisementRequestDto.class)
@JsonSubTypes({
        @JsonSubTypes.Type(name = AdvertisementType.Constants.CLOTHING, value = ClothingAdvertisementResponseDto.class),
        @JsonSubTypes.Type(name = AdvertisementType.Constants.SHOE, value = ShoeAdvertisementResponseDto.class)
})

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

    @Parameter(description = "the id of user who created this advertisement")
    long userId;

    @Parameter(description = "the status of advertisement")
    AdvertisementStatus status;

    @Parameter(description = "the address where advertisement locates")
    AdvertisementRequestAddressDto address;

    @Parameter(description = "the type of advertisement")
    AdvertisementType type;

    LocalDate createdAt;

}
