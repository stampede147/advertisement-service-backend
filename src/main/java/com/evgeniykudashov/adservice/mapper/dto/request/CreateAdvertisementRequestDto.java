package com.evgeniykudashov.adservice.mapper.dto.request;

import com.evgeniykudashov.adservice.mapper.dto.AddressDto;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdvertisementRequestDto implements Serializable {

    private String title;
    private String description;
    private long userId;
    private AddressDto address;
    private AdvertisementStatus status;
}
