package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Collection;

public interface MessageService {


    long createMessage(MessageRequestDto messageDto, Principal principal);

    void deleteMessage(long messageId);

    MessageResponseDto getMessageById(long messageId);

    PageDto<MessageResponseDto> getMessagesByChatId(long chatId, Pageable pageable);

    Collection<MessageResponseDto> getLastMessageByChatsIds(long[] chatId);
}
