package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping()
    public ResponseEntity<Void> onCreate(@RequestBody @Validated(value = CreateConstraint.class) AdvertisementRequestDto requestDto) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(advertisementService.createAdvertisement(requestDto)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> onDelete(@PathVariable Long id) {
        advertisementService.removeAdvertisementById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> onGetById(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.getOneByAdvertisementId(id));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<?> getUserAdvertisements(@RequestParam Long userId,
                                                   @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getPageByUserId(userId, pageable));
    }


    @GetMapping(params = "search")
    public ResponseEntity<?> searchAdvertisements(@RequestParam String search) {
        return null;
    }
}
