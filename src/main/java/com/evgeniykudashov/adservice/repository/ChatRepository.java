package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("from Chat c join c.advertisement ca where c.buyer.id =:userId or ca.seller.id = :userId")
    Page<Chat> findAllByUserId(long userId, Pageable pageable);
}
