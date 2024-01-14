package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.model.advertisement.ClothingSize;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeName(value = AdvertisementType.Constant.CLOTHING_ADVERTISEMENT)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothingAdvertisementRequestDto extends CSAAdvertisementRequestDto {

    public ClothingSize size;
}
