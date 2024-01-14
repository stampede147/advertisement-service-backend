package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CSAAdvertisementResponseDto extends AdvertisementResponseDto {

    public String healthCondition;

    public String brand;

    public String color;
}
