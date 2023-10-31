package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReviewResponseDto {

    public long id;

    public int rate;

    public String description;

    public LocalDate createdAt;

    public ReviewAdvertisementResponse advertisement;

}
