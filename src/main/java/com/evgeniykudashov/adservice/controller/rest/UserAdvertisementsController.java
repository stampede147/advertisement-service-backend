package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.AuthorizedUserAdvertisementService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;

@Tag(name = "User", description = "Provides API about Authenticated user Advertisements.")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/user/advertisements")
public class UserAdvertisementsController {

    private final AuthorizedUserAdvertisementService advertisementService;

    @Operation(tags = {"User", "Advertisement"},
            security = @SecurityRequirement(name = "jwt authentication"),
            description = "Creates a new advertisement owned by the authenticated user.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAdvertisement(Principal principal,
                                                    @Validated(value = CreateConstraint.class)
                                                    @RequestBody AdvertisementRequestDto dto) {
        long advertisementId = advertisementService.createAdvertisementByPrincipal(principal, dto);
        return ResponseEntity.created(MvcUriComponentsBuilder.fromController(AdvertisementsController.class)
                        .path("/{id}")
                        .build(advertisementId))
                .build();
    }

    @Operation(tags = {"User", "Advertisement"},
            description = "deletes advertisement",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "advertisement", description = "the id of advertisement"))
    @DeleteMapping(path = "/{advertisementId}")
    public void deleteAdvertisement(Principal principal,
                                    @PathVariable long advertisementId) {
        advertisementService.removeAdvertisementById(principal, advertisementId);
    }

    @Operation(tags = {"User", "Advertisement"},
            security = @SecurityRequirement(name = "jwt authentication"),
            description = "Returns paged array of advertisements owned by the authenticated user.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<? extends AdvertisementResponseDto>> onGetAll(Principal principal,
                                                                                @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getAdvertisementPage(principal, pageable));
    }


    @PostMapping("/{id}/active")
    public ResponseEntity<Void> makeAdvertisementActive(Principal principal,
                                                        @PathVariable Long id) {
        advertisementService.activateAdvertisementById(principal, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/hidden")
    public ResponseEntity<Void> makeAdvertisementHidden(Principal principal, @PathVariable Long id) {

        advertisementService.hideAdvertisementById(principal, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/viewed")
    public ResponseEntity<?> getViewedAdvertisement(Principal principal,
                                                    @PageableDefault(size = 5) Pageable pageable) {

        return ResponseEntity.ok(advertisementService.getViewedAdvertisements(principal, pageable));
    }
}