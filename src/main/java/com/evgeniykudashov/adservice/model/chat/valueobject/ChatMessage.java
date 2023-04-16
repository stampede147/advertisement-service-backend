package com.evgeniykudashov.adservice.model.chat.valueobject;

import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.chat.statuses.ChatStatus;
import com.evgeniykudashov.adservice.model.shared.CreationDateTime;
import com.evgeniykudashov.adservice.model.user.User;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private Chat chat;
    private MessageBody messageBody;
    private User sender;
    private ChatStatus chatStatus;
    private CreationDateTime createdDatetime;
}
