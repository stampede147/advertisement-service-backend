package com.evgeniykudashov.adservice.model.domain.aggregate.chat;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "chats")
public class Chat implements Serializable {

    @Id
    @Column(name = "chat_id")
    @EqualsAndHashCode.Include
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;


    @ElementCollection()
    @CollectionTable(name = "chat_messages", joinColumns = @JoinColumn(name = "chat_id"))
    private List<ChatMessage> chatMessages;

    public Chat(List<User> participants) {
        this.participants = participants;
        this.chatMessages = new ArrayList<>();
    }

    public void addChatMessage(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }

    public void addChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages.addAll(chatMessages);
    }

    public void deleteChatMessageByIndex(int index) {
        this.chatMessages.remove(index);
    }


    public void addParticipant(User user) {
        participants.add(user);
    }

    public void addParticipants(List<User> users) {
        participants.addAll(users);
    }

    public void removeParticipantByUser(User user) {
        participants.remove(user);
    }


}
