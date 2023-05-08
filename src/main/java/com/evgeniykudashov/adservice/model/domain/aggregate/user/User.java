package com.evgeniykudashov.adservice.model.domain.aggregate.user;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetailsImpl;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    @Getter
    private Long id;

    @Getter
    private PersonalDetails personalDetails;

    @Getter
    private AccessDetailsImpl accessDetails;

    public User(PersonalDetails personalDetails, AccessDetailsImpl accessDetails) {
        this.personalDetails = personalDetails;
        this.accessDetails = accessDetails;
    }

    public void changePersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public void changeAccessDetails(AccessDetailsImpl accessDetails) {
        this.accessDetails = accessDetails;
    }
}
