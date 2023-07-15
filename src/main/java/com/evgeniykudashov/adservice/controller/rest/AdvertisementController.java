package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.request.ClothingAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.request.ShoeAdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.ClothingAdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ShoeAdvertisementResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Advertisement", description = "Provides API about advertisement")

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(value = "/api/v1/advertisements",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Operation(description = "Creates an advertisement",
            tags = "Advertisement",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    anyOf = {
                                            ClothingAdvertisementRequestDto.class,
                                            ShoeAdvertisementRequestDto.class
                                    }
                            ))),
            security = @SecurityRequirement(name = "jwt authentication"),
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "(CREATED) Advertisement created successfully.",
                            headers = @Header(name = HttpHeaders.LOCATION,
                                    description = "The location of created resource"))})
    @PostMapping()
    public ResponseEntity<Void> onCreate(@RequestBody @Validated(value = CreateConstraint.class) AdvertisementRequestDto requestDto) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(advertisementService.createAdvertisement(requestDto)))
                .build();
    }

    @Operation(description = "deletes advertisement by id",
            tags = "Advertisement",
            parameters = @Parameter(name = "id", description = "the ID of advertisement that should be deleted"),
            security = @SecurityRequirement(name = "jwt authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "(OK) Advertisement deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) Advertisement with such ID not found to delete",
                            content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> onDelete(@PathVariable Long id) {
        advertisementService.removeAdvertisementById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "returns detailed advertisement by id",
            parameters = @Parameter(name = "id",
                    description = "the ID of advertisement"),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "(OK) Advertisement found and returned",
                            content = @Content(schema = @Schema(
                                    oneOf = {
                                            ClothingAdvertisementResponseDto.class,
                                            ShoeAdvertisementResponseDto.class
                                    }))),
                    @ApiResponse(responseCode = "404",
                            description = "(NOT FOUND) such advertisement with ID not found",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<? extends AdvertisementResponseDto> onGetById(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.getOneByAdvertisementId(id));
    }

    @Operation(description = "returns paged array of user non detailed advertisements",
            parameters = {
                    @Parameter(name = "userId", description = "the ID of user")
            })
    @GetMapping(params = "userId")
    public ResponseEntity<PageDto<? extends AdvertisementResponseDto>> getUserAdvertisements(@RequestParam Long userId,
                                                                                             @ParameterObject
                                                                                             @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getPageByUserId(userId, pageable));
    }


    @Operation(description = "search advertisements")
    @GetMapping(params = "search")
    public ResponseEntity<?> searchAdvertisements(@RequestParam String search) {
        return null;
    }
}
