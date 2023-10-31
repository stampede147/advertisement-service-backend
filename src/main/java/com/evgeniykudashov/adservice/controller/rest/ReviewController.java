package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.ReviewRequestDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewResponseDto;
import com.evgeniykudashov.adservice.service.ReviewService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/user/reviews")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addReview(@Validated(value = CreateConstraint.class)
                          @RequestBody ReviewRequestDto requestDto,
                          Principal principal) {

        reviewService.create(requestDto, principal);
    }


    @DeleteMapping(path = "/{id}")
    public void deleteReview(@PathVariable long id) {
        reviewService.delete(id);
    }


    @PostMapping(path = "/received", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PageDto<ReviewResponseDto> getReceivedReviews(Principal principal, @PageableDefault Pageable pageable) {

        return reviewService.getReceivedReviews(principal, pageable);
    }

    @PostMapping(path = "/sent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PageDto<ReviewResponseDto> getSentReviews(Principal principal, @PageableDefault Pageable pageable) {

        return reviewService.getSentReviews(principal, pageable);
    }
}
