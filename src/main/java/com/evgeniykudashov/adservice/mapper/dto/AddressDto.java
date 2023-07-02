package com.evgeniykudashov.adservice.mapper.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    Integer zipcode;

    String city;

    String street;

    String houseNumber;
}
