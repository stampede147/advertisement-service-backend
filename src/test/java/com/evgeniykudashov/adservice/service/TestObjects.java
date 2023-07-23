package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestAddressDto;
import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Address;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TestObjects {

    public static final long NOT_SAVED_ENTITY_ID = 0;
    public static final long SAVED_ENTITY_ID = 1;

    public AdvertisementRequestDto getNotSavedAdvertisementRequestDto() {
        Advertisement notSavedAdvertisement = getNotSavedAdvertisement();

        AdvertisementRequestDto dto = new AdvertisementRequestDto();
        dto.setAdvertisementId(NOT_SAVED_ENTITY_ID);
        dto.setPrice(notSavedAdvertisement.getPrice());
        dto.setStatus(notSavedAdvertisement.getStatus());
        dto.setTitle(notSavedAdvertisement.getTitle());
        dto.setDescription(notSavedAdvertisement.getDescription());
        dto.setUserId(notSavedAdvertisement.getOwner().getId());
        dto.setType(notSavedAdvertisement.getType());
        dto.setAddress(TestObjects.getAdvertisementRequestAddressDto());

        return dto;
    }

    public AdvertisementRequestDto getSavedAdvertisementRequestDto() {
        Advertisement notSavedAdvertisement = getNotSavedAdvertisement();

        AdvertisementRequestDto dto = new AdvertisementRequestDto();
        dto.setAdvertisementId(SAVED_ENTITY_ID);
        dto.setPrice(notSavedAdvertisement.getPrice());
        dto.setStatus(notSavedAdvertisement.getStatus());
        dto.setTitle(notSavedAdvertisement.getTitle());
        dto.setDescription(notSavedAdvertisement.getDescription());
        dto.setUserId(notSavedAdvertisement.getOwner().getId());
        dto.setType(notSavedAdvertisement.getType());
        dto.setAddress(TestObjects.getAdvertisementRequestAddressDto());

        return dto;
    }

    public Advertisement getNotSavedAdvertisement() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(NOT_SAVED_ENTITY_ID);
        advertisement.setPrice(100);
        advertisement.setStatus(AdvertisementStatus.ACTIVE);
        advertisement.setTitle("Title");
        advertisement.setAddress(TestObjects.getAddress());
        advertisement.setOwner(getSavedUser());
        return advertisement;
    }

    public Advertisement getSavedAdvertisement() {
        Advertisement advertisement = getNotSavedAdvertisement();
        advertisement.setId(SAVED_ENTITY_ID);

        return advertisement;
    }

    public AdvertisementResponseDto getAdvertisementResponseDto() {
        AdvertisementRequestDto advertisement = getSavedAdvertisementRequestDto();

        AdvertisementResponseDto responseDto = new AdvertisementResponseDto();
        responseDto.setAdvertisementId(advertisement.getAdvertisementId());
        responseDto.setAddress(advertisement.getAddress());
        responseDto.setType(advertisement.getType());
        responseDto.setTitle(advertisement.getTitle());
        responseDto.setStatus(advertisement.getStatus());
        responseDto.setDescription(advertisement.getDescription());
        responseDto.setUserId(advertisement.getUserId());

        return responseDto;
    }

    private AdvertisementRequestAddressDto getAdvertisementRequestAddressDto() {
        AdvertisementRequestAddressDto address = new AdvertisementRequestAddressDto();

        Address address1 = getAddress();
        address.setCity(address1.getCity());
        address.setStreet(address1.getStreet());
        address.setHouseNumber(address1.getHouseNumber());
        address.setZipcode(address1.getZipcode());

        return address;
    }

    private Address getAddress() {
        Address address = new Address();
        address.setCity("Moscow");
        address.setStreet("Alyabieva");
        address.setHouseNumber("12");
        address.setZipcode(120223);

        return address;
    }

    public User getNotSavedUser() {
        return User.builder()
                .role(Role.USER)
                .id(NOT_SAVED_ENTITY_ID)
                .password("password")
                .username("username")
                .birthdate(LocalDate.of(2023, 07, 22))
                .email("email@email.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
    }

    public User getSavedUser() {
        User notSavedUser = getNotSavedUser();
        return User.builder()
                .role(notSavedUser.getRole())
                .id(SAVED_ENTITY_ID)
                .password(notSavedUser.getPassword())
                .username(notSavedUser.getUsername())
                .birthdate(notSavedUser.getBirthdate())
                .email(notSavedUser.getEmail())
                .firstName(notSavedUser.getFirstName())
                .lastName(notSavedUser.getLastName())
                .build();
    }

}
