package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.FeedbackRequestDto;
import com.evgeniykudashov.adservice.dto.response.FeedbackResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.FeedbackService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Feedback", description = "provided API about feedbacks")
@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(description = "creates a feedback", responses = {
            @ApiResponse(responseCode = "201", description = "(CREATED) The resource created successfully.",
                    headers = @Header(name = HttpHeaders.LOCATION, description = "the location of created resource"))
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(value = CreateConstraint.class)
                                       FeedbackRequestDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/id}")
                        .build(feedbackService.createFeedback(dto)))
                .build();
    }

    @Operation(description = "deletes a feedback",
            parameters = @Parameter(name = "feedbackId", description = "ID of feedback, that should be deleted"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "(NO CONTENT) Feedback deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) such feedback ID not found")
            })
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> delete(@PathVariable long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Returns paged array of feedbacks. Such feedbacks correspond to seller (user) who received them",
            parameters = @Parameter(name = "sellerId", description = "User id who is the seller"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "(OK) feedbacks found and returned"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) such sellerId not found")
            }
    )
    @GetMapping(params = "sellerId")
    public ResponseEntity<PageDto<FeedbackResponseDto>> getBySellerId(@RequestParam long sellerId,
                                                                      @ParameterObject
                                                                      @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(feedbackService.findBySellerId(sellerId, pageable));
    }

    @Operation(description = "Returns paged array of feedbacks. Such feedbacks correspond to customer (user) who sends them",
            parameters = @Parameter(name = "customerId", description = "User id who is the customer"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "(OK) feedbacks found and returned"),
                    @ApiResponse(responseCode = "404", description = "(NOT FOUND) such customerId not found")
            })
    @GetMapping(params = "customerId")
    public ResponseEntity<PageDto<FeedbackResponseDto>> getByCustomerId(@RequestParam long customerId,
                                                                        @ParameterObject
                                                                        @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(feedbackService.findByCustomerId(customerId, pageable));
    }
}
