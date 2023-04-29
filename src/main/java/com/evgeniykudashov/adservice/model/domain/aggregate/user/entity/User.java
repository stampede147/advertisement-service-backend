package com.evgeniykudashov.adservice.model.domain.aggregate.user.entity;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.Fullname;
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
    private Fullname fullname;

    public User(Fullname fullname) {
        this.fullname = fullname;
    }

    public void changeFullName(Fullname fullname) {
        this.fullname = fullname;
    }
}
