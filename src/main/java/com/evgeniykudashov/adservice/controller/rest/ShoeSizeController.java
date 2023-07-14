package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.service.ShoeSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "Shoesize", description = "provided API about shoe sizes")


@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/shoesizes",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoeSizeController {

    private final ShoeSizeService shoeSizeService;

    @Operation(description = "creates new shoe size")
    @PostMapping
    public ResponseEntity<?> createShoeSize(ShoeSizeDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .build(shoeSizeService.create(dto)))
                .build();
    }

    @Operation(description = "deletes shoe size")
    @DeleteMapping("/{size}")
    public void deleteShoeSize(@PathVariable int size) {
        deleteShoeSize(size);
    }

    @Operation(description = "returns an array of shoe sizes")
    @GetMapping
    public List<ShoeSize> getShoeSizes() {
        return shoeSizeService.getShoeSizes();
    }


}
