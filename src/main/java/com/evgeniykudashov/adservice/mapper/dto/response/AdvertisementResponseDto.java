package com.evgeniykudashov.adservice.mapper.dto.response;


import com.evgeniykudashov.adservice.mapper.dto.request.AdvertisementRequestAddressDto;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
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

}
