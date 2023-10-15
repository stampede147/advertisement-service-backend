package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface ChatService {

    long create(ChatRequestDto dto, Principal principal);

    void remove(long chatId);

    PageDto<ChatResponseDto> findAllByPrincipal(Principal principal, Pageable pageable);

    ChatResponseDto getByChatId(Principal principal, long chatId);
}

