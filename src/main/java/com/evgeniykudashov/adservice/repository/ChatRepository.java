package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.chat.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("from Chat c join c.participants cp where cp.id = :userId")
    Page<Chat> findByUsersIds(long userId, Pageable pageable);

}
