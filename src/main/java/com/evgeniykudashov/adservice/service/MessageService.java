package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.MessageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface MessageService {


    long createMessage(MessageRequestDto messageDto);

    void deleteMessage(long messageId);

    MessageResponseDto getMessageById(long messageId);

    PageDto<MessageResponseDto> getLastMessagesByChatId(long chatId, Pageable pageable);

    Collection<MessageResponseDto> getLastMessageByChatsIds(long[] chatId);
}
