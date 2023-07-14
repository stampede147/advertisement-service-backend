package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.MessageMapper;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.MessageRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class MessageServiceImpl implements MessageService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final MessageMapper dtoMapper;

    @Override
    @Transactional
    public long createMessage(MessageRequestDto dto) {
        return messageRepository.save(dtoMapper.toMessage(dto, LocalDateTime.now(), MessageStatus.CREATED))
                .getId();
    }

    @Override
    @Transactional
    public void deleteMessage(long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public MessageResponseDto getMessageById(long messageId) {
        return dtoMapper.toMessageResponseDto(findMessageById(messageId));
    }

    private Message findMessageById(long messageId) {
        return messageRepository.findById(messageId).orElseThrow(NotFoundEntityException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<MessageResponseDto> getMessagesByChatId(long chatId, Pageable pageable) {
        return dtoMapper.toPageDto(messageRepository.findLastMessages(chatId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<MessageResponseDto> getLastMessageByChatsIds(long[] chatId) {
        return dtoMapper.toMessageResponseDto(messageRepository.findLastMessageByChatId(chatId));
    }
}
