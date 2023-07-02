package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.dto.MessageDto;
import com.evgeniykudashov.adservice.service.ChatService;
import com.evgeniykudashov.adservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/chats/{chatId}/messages")
public class ChatMessageController {

    private final ChatService chatService;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> addChatMessage(@RequestBody MessageDto dto,
                                               @PathVariable long chatId) {
        messageService.addMessage(dto, chatId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChatMessage(@RequestParam long messageId) {
        chatService.remove(messageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<?> getChatMessages(@PathVariable long chatId,
                                             @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(messageService.getLastMessages(chatId, pageable));
    }


}
