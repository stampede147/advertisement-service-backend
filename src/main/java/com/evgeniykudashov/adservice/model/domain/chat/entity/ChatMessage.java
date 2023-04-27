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

@Immutable
@Entity
@Table(name = "chat_messages")
public class ChatMessage implements Serializable {

    @Id
    @Column(name = "chat_message_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private MessageBody messageBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender_id")
    private User sender;

    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    public ChatMessage(long id, Chat chat, MessageBody messageBody, User sender, ChatStatus chatStatus) {
        this.id = id;
        this.chat = chat;
        this.messageBody = messageBody;
        this.sender = sender;
        this.chatStatus = chatStatus;
    }

    public ChatMessage withMessageBody(MessageBody messageBody) {
        return ChatMessage.builder()
                .id(this.id)
                .chat(this.chat)
                .messageBody(messageBody)
                .sender(this.sender)
                .chatStatus(ChatStatus.MODIFIED)
                .build();
    }
}
