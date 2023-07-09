package com.evgeniykudashov.adservice.dto.response;


import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoeAdvertisementResponseDto extends AdvertisementResponseDto {

    private ThingColor color;

    private ShoeSize size;

}
