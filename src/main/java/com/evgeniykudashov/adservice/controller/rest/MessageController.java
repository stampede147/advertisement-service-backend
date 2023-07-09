package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.service.MessageService;
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
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody @Validated(value = CreateConstraint.class) MessageRequestDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(messageService.createMessage(dto)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping(params = "chatsIds")
    public ResponseEntity<?> getLastMessage(@RequestParam long[] chatsIds) {
        return ResponseEntity.ok(messageService.getLastMessageByChatsIds(chatsIds));
    }

    @GetMapping(params = "chatId")
    public ResponseEntity<?> getLastMessage(@RequestParam long chatId,
                                            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(messageService.getLastMessagesByChatId(chatId, pageable));
    }
}
