package com.evgeniykudashov.adservice.dto.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AdvertisementParameterRequestDto {

    long parameterId;

    String value;
}
