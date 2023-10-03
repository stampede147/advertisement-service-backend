package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class LocationRequestDto {

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    public String city;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    public String street;
}
