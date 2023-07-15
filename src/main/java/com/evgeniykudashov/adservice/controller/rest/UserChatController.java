package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(value = "api/v1/users/{userId}/chats",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserChatController {

    private ChatService chatService;

    @Operation(description = "returns paged array of chats. In each of this chat userId is the participant",
            security = @SecurityRequirement(name = "jwt authentication"),
            tags = {"User", "Chat"})
    @GetMapping()
    public ResponseEntity<PageDto<ChatResponseDto>> findAll(@PathVariable long userId,
                                                            @ParameterObject
                                                            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(chatService.findAllByUserId(userId, pageable));
    }
}
