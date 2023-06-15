package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.ChatMapper;
import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.chat.ChatResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.chat.CreateChatRequestDto;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/users/{userId}/chats")
public class UserChatController {


    private ChatService chatService;
    private ChatMapper dtoEntityMapper;

    @PostMapping
    public ResponseEntity<Void> createChat(@RequestBody CreateChatRequestDto chat) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(chatService.create(dtoEntityMapper.ToChat(chat))))
                .build();
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable long chatId) {
        chatService.remove(chatId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @SuppressWarnings("unused")
    public ResponseEntity<PageDto<ChatResponseDto>> findAll(@PathVariable long userId,
                                                            @PageableDefault Pageable pageable) {

        return ResponseEntity.ok(dtoEntityMapper.toPageDto(chatService.findAllByUserId(userId, pageable)));
    }
}
