package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ChatRepository extends ListCrudRepository<Chat, Long> {


    @Query("from Chat c join c.participants cp where cp.id in :ids")
    Optional<Chat> findByUserParticipantsIds(@Param("ids") Collection<Long> ids);

    @Query("from Chat c join  c.participants cp where cp.id = :participantUserId")
    List<Chat> findAllByParticipantUserId(@Param("participantUserId") long participantUserId);


    // chat messages

    @Query("select cm from Chat c join c.chatMessages cm where c.id = :chatId")
    List<ChatMessage> findAllChatMessagesByChatId(@Param("chatId") long chatId);


    default Map<Long, List<ChatMessage>> findAllChatMessagesByChatsIds(@Param("chatsIds") Collection<Long> chatIds) {
        Map<Long, List<ChatMessage>> map = new HashMap<>();
        chatIds.forEach(chatId -> map.put(chatId, findAllChatMessagesByChatId(chatId)));
        return map;
    }
}
