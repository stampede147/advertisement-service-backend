package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/chats/{chatId}/messages")
public class ChatMessageController {

    private static final HttpStatus HTTP_STATUS_REDIRECT = HttpStatus.TEMPORARY_REDIRECT;
    private static final Class<MessageController> CONTROLLER_TYPE_REDIRECT = MessageController.class;

    private final MessageService messageService;

    private static ResponseEntity<Void> getConfiguredResponseEntity() {
        return ResponseEntity
                .status(HTTP_STATUS_REDIRECT)
                .location(MvcUriComponentsBuilder.fromController(CONTROLLER_TYPE_REDIRECT)
                        .build()
                        .toUri())
                .build();
    }

    @GetMapping()
    public ResponseEntity<?> getChatMessages(@PathVariable long chatId,
                                             @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(messageService.getLastMessagesByChatId(chatId, pageable));
    }

    @PostMapping
    public ResponseEntity<Void> addChatMessage() {
        return getConfiguredResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChatMessage() {
        return getConfiguredResponseEntity();
    }


}
