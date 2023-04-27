package com.evgeniykudashov.adservice.model.domain.aggregate.chat;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.entity.ChatMessage;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))

// aggregate
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @Column(name = "chat_id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_participants",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "user_id"))
    private List<User> participants;

    @ElementCollection()
    @CollectionTable(name = "chat_chat_messages", joinColumns = @JoinColumn(name = "chat_id"))
    private List<ChatMessage> chatMessages;


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
