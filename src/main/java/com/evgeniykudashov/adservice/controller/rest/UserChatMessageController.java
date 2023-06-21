package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.ChatMessageMapper;
import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.chat.ChatMessageDto;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/users/{userId}/chats/{chatId}/messages")
public class UserChatMessageController {

    private ChatService chatService;
    private ChatMessageMapper chatMessageMapper;

    @PostMapping
    public ResponseEntity<Void> addChatMessage(@RequestBody ChatMessageDto request, @PathVariable long chatId) {
        chatService.addChatMessage(chatMessageMapper.toEntity(request), chatId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChatMessage(@RequestBody ChatMessageDto request, @PathVariable long chatId) {
        chatService.removeChatMessage(chatMessageMapper.toEntity(request), chatId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<PageDto<ChatMessageDto>> getAll(@PathVariable long chatId,
                                                          @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(chatMessageMapper.toPageDto(chatService.findChatMessagesByChatId(chatId, pageable)));
    }


}
