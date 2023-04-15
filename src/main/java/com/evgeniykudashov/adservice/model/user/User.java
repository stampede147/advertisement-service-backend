package com.evgeniykudashov.adservice.model.user;


import com.evgeniykudashov.adservice.model.user.valueobject.Fullname;
import com.evgeniykudashov.adservice.model.user.valueobject.OnlineTime;
import com.evgeniykudashov.adservice.model.user.valueobject.UserSecretDetails;
import jakarta.persistence.Column;
import lombok.Getter;

public class User {

    @Column(name = "user_id")
    private Long id;

    private Fullname fullname;
    private UserSecretDetails userSecretDetails;
    private OnlineTime lastOnlineTime;


}
