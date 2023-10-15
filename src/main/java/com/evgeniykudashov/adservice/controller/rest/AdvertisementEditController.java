package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.response.EditLayoutResponseDto;
import com.evgeniykudashov.adservice.dto.response.NavigationResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/layouts/advertisement-edit")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementEditController {

    private final AdvertisementEditService advertisementEditService;

    @PostMapping(path = "/navigation",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NavigationResponseDto> loadNavigation() {
        return ResponseEntity.ok(advertisementEditService.loadNavigation());
    }


    @PostMapping(path = "/generate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditLayoutResponseDto> generateLayout(@RequestBody @Validated
                                                                NavigationResponseDto.NavigationDto dto) {
        return ResponseEntity.ok(advertisementEditService.loadLayout(dto));
    }


}
