package com.evgeniykudashov.adservice.model.domain.aggregate.Account;


import com.evgeniykudashov.adservice.model.domain.aggregate.Account.entity.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.Account.valueobject.AccountAccessDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@Getter

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private AccountAccessDetails accessDetails;

    public Account(User user, AccountAccessDetails accessDetails) {
        this.user = user;
        this.accessDetails = accessDetails;
    }


    public void changeAccessDetails(AccountAccessDetails accessDetails) {
        this.accessDetails = accessDetails;
    }


}
