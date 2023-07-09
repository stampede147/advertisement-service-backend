package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;

import java.util.List;

public interface ShoeSizeService {

    int create(ShoeSizeDto dto);

    void delete(int size);

    List<ShoeSize> getShoeSizes();
}
