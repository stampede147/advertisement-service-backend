package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class ChatResponseDto {

    long id;

    AdvertisementResponseDto advertisement;

    UserResponseDto buyer;

}
