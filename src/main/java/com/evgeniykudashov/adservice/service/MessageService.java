package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.mapper.dto.MessageDto;
import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface MessageService {


    long createMessage(MessageDto messageDto);

    void deleteMessage(long messageId);

    MessageDto getMessageById(long messageId);

    PageDto<MessageDto> getLastMessagesByChatId(long chatId, Pageable pageable);

    Collection<MessageDto> getLastMessageByChatsIds(long[] chatId);
}
