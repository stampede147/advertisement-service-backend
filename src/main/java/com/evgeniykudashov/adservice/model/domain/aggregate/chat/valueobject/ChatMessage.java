package com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode

@Immutable
@Embeddable
public class ChatMessage implements Serializable {


    private MessageBody body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;

    @Enumerated(EnumType.ORDINAL)
    @EqualsAndHashCode.Exclude
    private MessageStatus messageStatus;

    private PublicationDateTime publicationDateTime;

    @JsonCreator
    public ChatMessage(MessageBody messageBody, User sender, PublicationDateTime publicationDateTime) {
        this.body = messageBody;
        this.sender = sender;
        this.publicationDateTime = publicationDateTime;
        this.messageStatus = MessageStatus.CREATED;
    }

    @JsonCreator
    public ChatMessage(MessageBody body, User sender, MessageStatus messageStatus, PublicationDateTime publicationDateTime) {
        this.body = body;
        this.sender = sender;
        this.messageStatus = messageStatus;
        this.publicationDateTime = publicationDateTime;
    }

    public ChatMessage withUpdatedMessageBody(MessageBody messageBody) {
        return new ChatMessage(messageBody, this.sender, MessageStatus.MODIFIED, this.publicationDateTime);
    }

    public ChatMessage withDeletedStatus() {
        return new ChatMessage(this.body, this.sender, MessageStatus.DELETED, this.publicationDateTime);
    }
}
