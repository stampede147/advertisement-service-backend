package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.mapper.dto.request.FeedbackRequestDto;
import com.evgeniykudashov.adservice.service.FeedbackService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated(value = CreateConstraint.class)
                                    FeedbackRequestDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/id}")
                        .build(feedbackService.createFeedback(dto)))
                .build();
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> delete(@PathVariable long feedbackId) {
        delete(feedbackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "sellerId")
    public ResponseEntity<?> getBySellerId(@RequestParam long sellerId,
                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(feedbackService.findBySellerId(sellerId, pageable));
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<?> getByCustomerId(@RequestParam long customerId,
                                             @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(feedbackService.findByCustomerId(customerId, pageable));
    }
}
