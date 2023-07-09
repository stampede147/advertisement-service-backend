package com.evgeniykudashov.adservice.dto.response;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestAddressDto;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdvertisementResponseDto {

    long advertisementId;

    String title;

    String description;

    long userId;

    AdvertisementStatus status;

    AdvertisementRequestAddressDto address;

    AdvertisementType type;

}
