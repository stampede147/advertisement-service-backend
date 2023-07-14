package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.MessageService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Tag(name = "Message", description = "provided API about chat messaging")

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(value = "/api/v1/chat-messages",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final MessageService messageService;

    @Operation(description = "creates message", tags = "Message")
    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody @Validated(value = CreateConstraint.class) MessageRequestDto dto) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(messageService.createMessage(dto)))
                .build();
    }

    @Operation(description = "deletes message", tags = "Message")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "returns message by its ID", tags = "Message")
    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @Operation(description = "Returns an array of last messages. Each lastMessage corresponds concrete chat id.",
            tags = "Message",
            parameters = @Parameter(name = "chatsIds", description = "chat ids"))
    @GetMapping(params = "chatsIds")
    public ResponseEntity<Collection<MessageResponseDto>> getLastMessage(@RequestParam long[] chatsIds) {
        return ResponseEntity.ok(messageService.getLastMessageByChatsIds(chatsIds));
    }

    @Operation(description = "returns paged array of messages. Messages corresponds chatId",
            parameters = @Parameter(name = "chatId", description = "chat id"))
    @GetMapping(params = "chatId")
    public ResponseEntity<PageDto<MessageResponseDto>> getLastMessage(@RequestParam long chatId,
                                                                      @ParameterObject
                                                                      @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(messageService.getMessagesByChatId(chatId, pageable));
    }
}
