package com.evgeniykudashov.adservice.model.domain.user;


import com.evgeniykudashov.adservice.model.domain.user.valueobject.Fullname;
import com.evgeniykudashov.adservice.model.domain.user.valueobject.UserSecretDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    private Fullname fullname;
    private UserSecretDetails secretDetails;

    public User() {
    }

    public User(Fullname fullname,
                UserSecretDetails secretDetails) {
        this.fullname = fullname;
        this.secretDetails = secretDetails;
    }

    public void changeFullName(Fullname fullname) {
        this.fullname = fullname;
    }

    public void changeSecretDetails(UserSecretDetails secretDetails) {
        this.secretDetails = secretDetails;
    }


}
