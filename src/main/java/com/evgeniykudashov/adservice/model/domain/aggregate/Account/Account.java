package com.evgeniykudashov.adservice.model.domain.aggregate.Account;


import com.evgeniykudashov.adservice.model.domain.aggregate.Account.entity.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.Account.valueobject.AccessDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
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

    private AccessDetails accessDetails;


    public Account(AccessDetails accessDetails) {
        this.accessDetails = accessDetails;
    }

    public void changeAccessDetails(AccessDetails accessDetails) {
        this.accessDetails = accessDetails;
    }


}
