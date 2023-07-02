package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("from Message m where m.id in (select max(m1.id) from Message m1 where m1.chat.id in :chatIds group by m1.chat)")
    Collection<Message> findLastMessageByChatId(long[] chatIds);

    @Query("from Message m order by m.id desc")
    Page<Message> findLastMessages(long chatId, Pageable pageable);
}
