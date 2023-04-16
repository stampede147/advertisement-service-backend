package com.evgeniykudashov.adservice.model.user;


import com.evgeniykudashov.adservice.model.shared.ActivityDateTime;
import com.evgeniykudashov.adservice.model.user.valueobject.Fullname;
import com.evgeniykudashov.adservice.model.user.valueobject.UserSecretDetails;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class User {

    @Column(name = "user_id")
    private Long id;

    private Fullname fullname;
    private UserSecretDetails secretDetails;
    private ActivityDateTime activityDateTime;

    public User() {
    }

    public User(Fullname fullname,
                UserSecretDetails secretDetails, LocalDateTime creationDateTime) {
        this.fullname = fullname;
        this.secretDetails = secretDetails;
        this.activityDateTime = ActivityDateTime.startFrom(creationDateTime);
    }

    public void changeFullName(Fullname fullname, LocalDateTime changeDateTime) {
        this.fullname = fullname;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);
    }

    public void changeSecretDetails(UserSecretDetails secretDetails, LocalDateTime changeDateTime) {
        this.secretDetails = secretDetails;
        this.activityDateTime = activityDateTime.withLastUpdateDateTime(changeDateTime);
    }


}
