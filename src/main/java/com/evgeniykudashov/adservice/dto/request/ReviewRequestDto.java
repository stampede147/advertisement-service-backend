package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.Rate;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ReviewRequestDto {

    @Positive(groups = CreateConstraint.class)
    public long advertisementId;

    @NotEmpty(groups = CreateConstraint.class)
    public String description;

    @NotNull(groups = CreateConstraint.class)
    public Rate rate;
}
