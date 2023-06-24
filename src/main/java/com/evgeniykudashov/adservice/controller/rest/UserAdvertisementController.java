package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.dto.advertisement.CreateAdvertisementRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.advertisement.UpdateAdvertisementRequestDto;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/advertisements")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserAdvertisementController {

    private AdvertisementService advertisementService;
    private AdvertisementMapper dtoEntityMapper;

    @PostMapping()
    public ResponseEntity<Void> onCreate(@RequestBody CreateAdvertisementRequestDto requestDto) {
        long resourceId = advertisementService.create(dtoEntityMapper.toAdvertisement(requestDto));
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(resourceId))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> onPut(@RequestBody UpdateAdvertisementRequestDto requestDto, @PathVariable Long id) {
        Advertisement advertisement = dtoEntityMapper.toAdvertisement(requestDto);
        advertisementService.put(advertisement, id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<?> handlePatchMethodExceptions(MismatchedInputException e) {
        return ResponseEntity.badRequest().body(Map.of("message", "unknown properties found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> onDelete(@PathVariable Long id) {
        advertisementService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> onGetById(@PathVariable Long id) {
        return ResponseEntity.ok(dtoEntityMapper.toAdvertisementResponseDto(advertisementService.findById(id)));
    }

    @GetMapping()
    public ResponseEntity<?> onGetAll(@PathVariable Long userId,
                                      @PageableDefault Pageable pageable) {
        Page<Advertisement> allByUserId = advertisementService.findAllByUserId(userId, pageable);
        return ResponseEntity.ok(
                dtoEntityMapper.toPageDto(allByUserId));
    }
}
