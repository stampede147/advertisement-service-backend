package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import com.evgeniykudashov.adservice.service.ThingColorService;
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

@Tag(name = "ThingColor", description = "provided API about thing colors")

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/thingcolors",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ThingColorController {

    private final ThingColorService thingColorService;

    @Operation(description = "creates the color")
    @ApiResponse(responseCode = "201",
            description = "created successfully",
            headers = @Header(name = HttpHeaders.LOCATION, description = "The location of created resource"))
    @PostMapping
    public ResponseEntity<?> createThingColor(@RequestBody ThingColor color) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .build(thingColorService.createThingColor(color)))
                .build();
    }

    @Operation(description = "deletes the color",
            responses = {
                    @ApiResponse(responseCode = "200", description = "(OK) Deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) Not found such color by ID")
            })
    @DeleteMapping(params = "colorId")
    public void deleteThingColor(@RequestParam String colorId) {
        this.thingColorService.deleteThingColorByColor(colorId);
    }

    @Operation(description = "returns an array of colors")
    @ApiResponse(responseCode = "200", description = "(OK) returns colors")
    @GetMapping
    public List<ThingColor> getShoeSizes() {
        return thingColorService.getThingColors();
    }


}
