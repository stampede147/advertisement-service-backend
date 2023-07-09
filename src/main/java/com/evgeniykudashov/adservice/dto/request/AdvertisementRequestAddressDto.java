package com.evgeniykudashov.adservice.dto.request;


import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementRequestAddressDto {

    @Min(value = 0, message = "zipcode should contain only numbers", groups = {CreateConstraint.class, UpdateConstraint.class})
    Integer zipcode;

    @Pattern(regexp = "^[a-zA-Z. ]+", message = "city should contain any letter", groups = {CreateConstraint.class, UpdateConstraint.class})
    String city;

    @Pattern(regexp = "^[a-zA-Z. ]+", message = "street should contain any letter", groups = {CreateConstraint.class, UpdateConstraint.class})
    String street;

    @Pattern(regexp = "^[0-9]+$", message = "house number should contain any number", groups = {CreateConstraint.class, UpdateConstraint.class})
    String houseNumber;
}
