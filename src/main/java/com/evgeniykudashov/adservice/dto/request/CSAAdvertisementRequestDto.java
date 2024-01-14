package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@JsonTypeName(value = AdvertisementType.Constant.CSA_ADVERTISEMENT)
@Data
public class CSAAdvertisementRequestDto extends AdvertisementRequestDto {

    public String healthCondition;

    public String brand;

    public String color;
}
