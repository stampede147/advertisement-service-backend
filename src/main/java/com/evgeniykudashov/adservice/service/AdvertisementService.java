package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.view.dto.AdvertisementDto;
import com.evgeniykudashov.adservice.view.dto.CategoryDto;
import com.evgeniykudashov.adservice.view.dto.UserDto;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDto create(AdvertisementDto advertisementDto);

    AdvertisementDto update(AdvertisementDto advertisementDto);

    void delete(AdvertisementDto advertisementDto);

    public List<AdvertisementDto> findAllByUser(UserDto user);

    public List<AdvertisementDto> findAllByTitle(Title title);

    public List<AdvertisementDto> findAllByCategory(CategoryDto categoryDto);


}
