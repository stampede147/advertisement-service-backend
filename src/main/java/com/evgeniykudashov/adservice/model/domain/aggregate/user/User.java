package com.evgeniykudashov.adservice.model.domain.aggregate.user;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private PersonalDetails personalDetails;

    private AccessDetails accessDetails;

    public User(PersonalDetails personalDetails, AccessDetails accessDetails) {
        this.personalDetails = personalDetails;
        this.accessDetails = accessDetails;
    }

    public void changePersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public void changeAccessDetails(AccessDetails accessDetails) {
        this.accessDetails = accessDetails;
    }
}
