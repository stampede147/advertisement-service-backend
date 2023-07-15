package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.service.ShoeSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @ApiResponse(responseCode = "201",
            description = "created successfully",
            headers = @Header(name = HttpHeaders.LOCATION, description = "The location of created resource"))
    @PostMapping
    public ResponseEntity<?> createShoeSize(ShoeSizeDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .build(shoeSizeService.create(dto)))
                .build();
    }

    @Operation(description = "deletes shoe size by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "(OK) Deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) Not found such shoe size by ID")
            })
    @DeleteMapping("/{sizeId}")
    public void deleteShoeSize(@PathVariable int sizeId) {
        shoeSizeService.delete(sizeId);
    }

    @Operation(description = "returns an array of shoe sizes")
    @ApiResponse(responseCode = "200", description = "(OK) returns shoe sizes")
    @GetMapping
    public List<ShoeSize> getShoeSizes() {
        return shoeSizeService.getShoeSizes();
    }


}
