package com.evgeniykudashov.adservice.controller.mapper.dto.advertisement;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class AdvertisementCreateRequestDto {

    Title title;
    Description description;
    long categoryId;
    long userId;
    Address address;
}
