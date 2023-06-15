package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.FeedbackMapper;
import com.evgeniykudashov.adservice.mapper.dto.feedback.FeedbackCreateRequestDto;
import com.evgeniykudashov.adservice.service.FeedbackService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users/{userId}/feedbacks")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FeedbackController {

    private FeedbackService feedbackService;

    private FeedbackMapper feedbackMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FeedbackCreateRequestDto requestDto) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(feedbackService.create(feedbackMapper.toFeedback(requestDto))))
                .build();
    }

    @DeleteMapping("/{advId}")
    public ResponseEntity<?> delete(@PathVariable long advId) {
        feedbackService.remove(advId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    @SneakyThrows
    public ResponseEntity<?> getAll(@PathVariable long userId) {
        return ResponseEntity.ok(feedbackMapper.toFeedbackResponseDto(feedbackService.findAllByRecipientUserId(userId)));
    }

}
