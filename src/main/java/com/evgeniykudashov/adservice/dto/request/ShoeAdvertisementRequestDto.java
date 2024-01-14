package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@JsonTypeName(value = AdvertisementType.Constant.SHOE_ADVERTISEMENT)
@Data
public class ShoeAdvertisementRequestDto extends CSAAdvertisementRequestDto {

    public ShoeSize size;
}
