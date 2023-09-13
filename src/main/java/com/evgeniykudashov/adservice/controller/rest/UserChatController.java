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
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;

@Tag(name = "Chat", description = "Provides API about chatting")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/user/chats")
public class UserChatController {

    private final ChatService chatService;

    @Operation(description = "Creates chat by the authenticated user",
            tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            responses = @ApiResponse(responseCode = "201",
                    description = "Chat created successfully",
                    headers = @Header(name = HttpHeaders.LOCATION, description = "The location of created chat resource")))
    @PostMapping
    public ResponseEntity<Void> createChat(@RequestBody @Validated(value = CreateConstraint.class)
                                           ChatRequestDto chat,
                                           Principal principal) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(chatService.create(chat, principal)))
                .build();
    }

    @Operation(description = "Deletes chat by the authenticated user", tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "chatId", description = "The ID of chat, that should be deleted"),
            responses = @ApiResponse(responseCode = "204", description = "Chat deleted successfully"))
    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable long chatId) {
        chatService.remove(chatId);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Returns chat where participant - authenticated user", tags = "Chat",
            security = @SecurityRequirement(name = "jwt authentication"),
            parameters = @Parameter(name = "chatId", description = "The ID of chat"),
            responses = @ApiResponse(responseCode = "200", description = "(OK) The request was performed successfully"))
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponseDto> getOne(Principal principal, @PathVariable long chatId) {
        return ResponseEntity.ok(chatService.getByChatId(principal, chatId));
    }

    @Operation(description = "returns paged array of chats with participant - authenticated user",
            security = @SecurityRequirement(name = "jwt authentication"),
            tags = {"User", "Chat"})
    @GetMapping()
    public ResponseEntity<PageDto<ChatResponseDto>> findAll(Principal principal,
                                                            @ParameterObject
                                                            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(chatService.findAllByPrincipal(principal, pageable));
    }
}
