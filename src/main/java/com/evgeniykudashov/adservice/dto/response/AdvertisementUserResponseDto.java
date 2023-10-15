package com.evgeniykudashov.adservice.dto.response;

import lombok.Data;

@Data
public class AdvertisementUserResponseDto {

    public long id;

    public String firstName;

    public String lastName;

    public ImageEntityResponseDto image;

}
