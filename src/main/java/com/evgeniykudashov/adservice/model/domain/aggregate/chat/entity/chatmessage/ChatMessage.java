package com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.chatmessage.valueobject.MessageBody;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor

@Immutable
@Embeddable
public class ChatMessage implements Serializable {

    private MessageBody messageBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id")
    private User sender;

    @Enumerated(EnumType.ORDINAL)
    private MessageStatus messageStatus;


    public ChatMessage(MessageBody messageBody, User sender) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.messageStatus = MessageStatus.CREATED;
    }

    public ChatMessage withMessageBody(MessageBody messageBody) {
        return new ChatMessage(messageBody, this.sender, MessageStatus.MODIFIED);
    }

    public void makeDeleted() {
        this.messageStatus = MessageStatus.DELETED;
    }
}
