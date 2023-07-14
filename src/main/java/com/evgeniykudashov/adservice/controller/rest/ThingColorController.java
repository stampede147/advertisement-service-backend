package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import com.evgeniykudashov.adservice.service.ThingColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping
    public ResponseEntity<?> createThingColor(@RequestBody ThingColor color) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .build(thingColorService.createThingColor(color)))
                .build();
    }

    @Operation(description = "deletes the color")
    @DeleteMapping(params = "color")
    public void deleteThingColor(@RequestParam String color) {
        this.thingColorService.deleteThingColorByColor(color);
    }

    @Operation(description = "returns an array of colors")
    @GetMapping
    public List<ThingColor> getShoeSizes() {
        return thingColorService.getThingColors();
    }


}
