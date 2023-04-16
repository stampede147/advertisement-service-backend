package com.evgeniykudashov.adservice.model.chat;

import com.evgeniykudashov.adservice.model.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.model.shared.ActivityDateTime;
import com.evgeniykudashov.adservice.model.shared.CreationDateTime;
import com.evgeniykudashov.adservice.model.user.User;

import java.util.List;

public class Chat {

    private long id;

    private List<User> participants;

    private List<ChatMessage> messages;

    private CreationDateTime creationDateTime;


}
