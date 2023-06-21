package com.evgeniykudashov.adservice.mapper.dto.advertisement;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class AdvertisementUpdateRequestDto {

    Title title;
    Description description;
    Address address;
    AdvertisementStatus status;
}
