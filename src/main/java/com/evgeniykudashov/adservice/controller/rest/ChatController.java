package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.mapper.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.service.ChatService;
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
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Void> createChat(@RequestBody @Validated(value = CreateConstraint.class) ChatRequestDto chat) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(chatService.create(chat)))
                .build();
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable long chatId) {
        chatService.remove(chatId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<?> getOne(@PathVariable long chatId) {
        return ResponseEntity.ok(chatService.findById(chatId));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<?> findAll(@RequestParam long userId,
                                     @PageableDefault Pageable pageable) {

        return ResponseEntity.ok(chatService.findAllByUserId(userId, pageable));
    }
}
