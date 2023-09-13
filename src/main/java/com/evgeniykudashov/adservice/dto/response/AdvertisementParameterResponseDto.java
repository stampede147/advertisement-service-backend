package com.evgeniykudashov.adservice.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AdvertisementParameterResponseDto {

    Long parameterId;

    String name;

    String value;
}
