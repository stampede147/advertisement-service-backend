package com.evgeniykudashov.adservice.model.chat;


import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Builder
//
@Entity
@Table(name = "chat_messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", updatable = false)
    @Immutable
    private Chat chat;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @Immutable
    private User sender;

    private LocalDateTime createdAt;

    private MessageStatus status;

    public Message(long id,
                   @NonNull Chat chat,
                   @NonNull String body,
                   @NonNull User sender,
                   @NonNull LocalDateTime createdAt,
                   @NonNull MessageStatus status) {
        this.id = id;
        this.chat = chat;
        this.body = body;
        this.sender = sender;
        this.createdAt = createdAt;
        this.status = status;
    }
}
