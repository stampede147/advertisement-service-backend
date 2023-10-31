package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReviewAdvertisementResponse {

    public String id;

    public String title;

    public String seller;
}
