package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.ChatResponseDto;
import org.springframework.data.domain.Pageable;

public interface ChatService {

    /**
     * chat segment begin
     */
    long create(ChatRequestDto dto);

    void remove(long chatId);

    PageDto<ChatResponseDto> findAllByUserId(long userId, Pageable pageable);

    ChatResponseDto findById(long chatId);

    /**
     *  chat segment end
     */
}
