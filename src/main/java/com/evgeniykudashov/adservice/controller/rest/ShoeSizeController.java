package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.service.ShoeSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/shoesizes",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoeSizeController {

    private final ShoeSizeService shoeSizeService;

    @PostMapping
    public ResponseEntity<?> createShoeSize(ShoeSizeDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .build(shoeSizeService.create(dto)))
                .build();
    }

    @DeleteMapping("/{size}")
    public void deleteShoeSize(@PathVariable int size) {
        deleteShoeSize(size);
    }

    @GetMapping
    public List<ShoeSize> getShoeSizes() {
        return shoeSizeService.getShoeSizes();
    }


}
