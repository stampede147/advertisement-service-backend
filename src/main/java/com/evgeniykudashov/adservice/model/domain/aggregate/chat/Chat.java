package com.evgeniykudashov.adservice.model.domain.aggregate.chat;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor

@Entity
@Table(name = "chats")
@Setter
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    @EqualsAndHashCode.Include
    @Getter
    private long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    @Immutable
    private Advertisement advertisement;

    @OneToMany()
    @JoinTable(name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Immutable
    private List<User> participants;

    @ElementCollection()
    @CollectionTable(name = "chat_messages", joinColumns = @JoinColumn(name = "chat_id"))
    @Getter
    private List<ChatMessage> chatMessages;

    public Chat(Advertisement advertisement, List<User> participants) {
        this.advertisement = advertisement;
        this.participants = participants;
        this.chatMessages = new ArrayList<>();
    }

    public void addChatMessage(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }

    public void removeChatMessage(ChatMessage chatMessage) {
        this.chatMessages.remove(chatMessage);
    }


}
