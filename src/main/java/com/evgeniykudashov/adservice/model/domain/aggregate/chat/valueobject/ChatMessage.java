package com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;


@Getter
@Setter
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

    private PublicationDateTime publicationDateTime;

    public ChatMessage(MessageBody messageBody, User sender, PublicationDateTime publicationDateTime) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.publicationDateTime = publicationDateTime;
        this.messageStatus = MessageStatus.CREATED;
    }

    public ChatMessage withUpdatedMessageBody(MessageBody messageBody) {
        return new ChatMessage(messageBody, this.sender, MessageStatus.MODIFIED, this.publicationDateTime);
    }

    public ChatMessage withDeletedStatus() {
        return new ChatMessage(this.messageBody, this.sender, MessageStatus.DELETED, this.publicationDateTime);
    }
}
