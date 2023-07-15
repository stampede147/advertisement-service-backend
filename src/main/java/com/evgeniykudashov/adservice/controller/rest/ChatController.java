package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.ChatService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Chat", description = "Provides API about chatting")

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(value = "/api/v1/chats",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {

    private final ChatService chatService;

    @Operation(description = "Creates chat",
            tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            responses = @ApiResponse(responseCode = "201",
                    description = "Chat created successfully",
                    headers = @Header(name = HttpHeaders.LOCATION, description = "The location of created chat resource")))
    @PostMapping
    public ResponseEntity<Void> createChat(@RequestBody @Validated(value = CreateConstraint.class) ChatRequestDto chat) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(chatService.create(chat)))
                .build();
    }

    @Operation(description = "Deletes chat by its id", tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "chatId", description = "The ID of chat, that should be deleted"),
            responses = @ApiResponse(responseCode = "200", description = "Chat deleted successfully"))
    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable long chatId) {
        chatService.remove(chatId);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Returns chat by its id", tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "chatId", description = "The ID of chat"),
            responses = @ApiResponse(responseCode = "200", description = "Such chat was found and returned"))
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDto> getOne(@PathVariable long chatId) {
        return ResponseEntity.ok(chatService.findById(chatId));
    }

    @Operation(description = "Returns paged array of chats, with participant - userId",
            tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "userId", description = "Participant of chat"),
            responses = @ApiResponse(responseCode = "200", description = "Returns page of chats"))
    @GetMapping(params = "userId")
    public ResponseEntity<PageDto<ChatResponseDto>> findAll(@RequestParam long userId,
                                                            @ParameterObject
                                                            @PageableDefault Pageable pageable) {

        return ResponseEntity.ok(chatService.findAllByUserId(userId, pageable));
    }
}
