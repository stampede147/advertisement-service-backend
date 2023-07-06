package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.ChatMapper;
import com.evgeniykudashov.adservice.mapper.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    private AdvertisementRepository advertisementRepository;
    private UserRepository userRepository;

    private ChatMapper dtoMapper;

    @Override
    @Transactional
    public long create(ChatRequestDto dto) {
        return chatRepository.save(dtoMapper.toChat(dto, LocalDate.now()))
                .getId();

    }

    private Set<User> convertUserIdToUser(Set<Long> ids) {
        return ids.stream()
                .map(userId -> userRepository.getReferenceById(userId))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void remove(long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<ChatResponseDto> findAllByUserId(long userId, Pageable pageable) {
        return dtoMapper.toPageDto(chatRepository.findByUsersIds(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public ChatResponseDto findById(long chatId) {
        return dtoMapper.toChatResponseDto(findChatById(chatId));

    }

    private Chat findChatById(long chatId) {
        return chatRepository.findById(chatId).orElseThrow(NotFoundEntityException::new);
    }
}
