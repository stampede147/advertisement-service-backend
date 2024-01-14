package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ShoeAdvertisementResponseDto extends CSAAdvertisementResponseDto {

    ShoeSize size;
}
