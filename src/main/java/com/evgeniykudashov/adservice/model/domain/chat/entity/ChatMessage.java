package com.evgeniykudashov.adservice.model.domain.chat.entity;

import com.evgeniykudashov.adservice.model.domain.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.chat.statuses.ChatStatus;
import com.evgeniykudashov.adservice.model.domain.chat.valueobject.MessageBody;
import com.evgeniykudashov.adservice.model.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@Builder

@Embeddable
public class ChatMessage implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @Immutable
    private Chat chat;

    private MessageBody messageBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender_id")
    @Immutable
    private User sender;

    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    public ChatMessage(Chat chat, MessageBody messageBody, User sender, ChatStatus chatStatus) {
        this.chat = chat;
        this.messageBody = messageBody;
        this.sender = sender;
        this.chatStatus = chatStatus;
    }

    public ChatMessage withMessageBody(MessageBody messageBody) {
        return ChatMessage.builder()
                .chat(this.chat)
                .messageBody(messageBody)
                .sender(this.sender)
                .chatStatus(ChatStatus.MODIFIED)
                .build();
    }
}
