package com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage.valueobject.MessageBody;
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
    private MessageStatus messageStatus;


    public ChatMessage(MessageBody messageBody, User sender) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.messageStatus = MessageStatus.CREATED;
    }

    protected ChatMessage(MessageBody messageBody, User sender, MessageStatus chatStatus) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.messageStatus = chatStatus;
    }

    public ChatMessage withMessageBody(MessageBody messageBody) {
        return new ChatMessage(messageBody, this.sender, MessageStatus.MODIFIED);
    }

    public void makeDeleted() {
        this.messageStatus = MessageStatus.DELETED;
    }
}
