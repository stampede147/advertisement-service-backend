package com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.statuses.ChatStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.MessageBody;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
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

    private MessageBody messageBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender_id")
    @Immutable
    private User sender;

    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;


    public ChatMessage(MessageBody messageBody, User sender) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.chatStatus = ChatStatus.CREATED;

    }

    protected ChatMessage(MessageBody messageBody, User sender, ChatStatus chatStatus) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.chatStatus = chatStatus;
    }

    public ChatMessage withMessageBody(MessageBody messageBody) {
        return new ChatMessage(messageBody, this.sender, ChatStatus.MODIFIED);
    }

    public void makeDeleted() {
        this.chatStatus = ChatStatus.DELETED;
    }
}
