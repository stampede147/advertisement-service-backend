package com.evgeniykudashov.adservice.controller.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {


    @Query("from Chat c join c.participants cp where cp.id in :ids")
    Optional<Chat> findByUserParticipantsIds(@Param("ids") Collection<Long> ids);

    @Query("from Chat c join c.participants cp where cp.id = :userId")
    Page<Chat> findAllByUserId(@Param("userId") long userId, Pageable pageable);


    // chat messages

    @Query("select cm from Chat c join c.chatMessages cm where c.id = :chatId")
    Page<ChatMessage> findAllChatMessagesByChatId(@Param("chatId") long chatId, Pageable pageable);

}
