package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/advertisements")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdvertisementsController {

    private final AdvertisementService advertisementService;

    @Operation(tags = {"User", "Advertisement"},
            description = "Returns paged array of advertisements that owned by provided userId.",
            parameters =  @Parameter(name = "userId", description = "the id of user"))
    @GetMapping(params = "userId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<? extends AdvertisementResponseDto>> onGetAll(@RequestParam long userId,
                                                                                @ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getPageByUserId(userId, pageable));
    }

    @GetMapping(value = "/{advertisementId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvertisementResponseDto> getOne(@PathVariable long advertisementId) {
        return ResponseEntity.ok(advertisementService.getOneByAdvertisementId(advertisementId));
    }
}
