package com.evgeniykudashov.adservice.model.domain.chat;

import com.evgeniykudashov.adservice.model.domain.chat.entity.ChatMessage;
import com.evgeniykudashov.adservice.model.domain.user.User;
import jakarta.persistence.*;

import java.util.List;


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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_message_id")
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
