package com.evgeniykudashov.adservice.repository.chatrepository;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import org.springframework.data.repository.ListCrudRepository;

public interface ChatRepository extends ListCrudRepository<Chat, Long> {
}
