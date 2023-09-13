package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.controller.ControllerDomainConstants;
import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.AdvertisementService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import java.security.Principal;

@Tag(name = "User", description = "Provides API about Authenticated user Advertisements.")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/user/advertisements")
public class UserAdvertisementsController {

    private final AdvertisementService advertisementService;

    @Operation(tags = {ControllerDomainConstants.USER, ControllerDomainConstants.ADVERTISEMENT},
            security = @SecurityRequirement(name = "jwt authentication"),
            description = "Creates a new advertisement owned by the authenticated user.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAdvertisement(Principal principal, @RequestBody AdvertisementRequestDto dto) {
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
    public void deleteAdvertisement(@PathVariable long advertisementId) {
        advertisementService.removeAdvertisementById(advertisementId);
    }

    @Operation(tags = {ControllerDomainConstants.USER, ControllerDomainConstants.ADVERTISEMENT},
            security = @SecurityRequirement(name = "jwt authentication"),
            description = "Returns paged array of advertisements owned by the authenticated user.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<? extends AdvertisementResponseDto>> onGetAll(Principal principal,
                                                                                @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getPageByPrincipal(principal, pageable));
    }

    @GetMapping(value = "/{advertisementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvertisementResponseDto> getOne(@PathVariable long advertisementId) {
        return ResponseEntity.ok(advertisementService.getOneByAdvertisementId(advertisementId));
    }


}