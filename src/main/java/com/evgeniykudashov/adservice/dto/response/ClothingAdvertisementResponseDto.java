package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.model.advertisement.ClothingSize;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@JsonTypeName(value = AdvertisementType.Constant.CLOTHING_ADVERTISEMENT)
@Data
public class ClothingAdvertisementResponseDto extends CSAAdvertisementResponseDto {

    ClothingSize size;

}
