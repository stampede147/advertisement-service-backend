package com.evgeniykudashov.adservice.model.domain.aggregate.user;


import com.evgeniykudashov.adservice.annotation.Default;
import com.evgeniykudashov.adservice.model.domain.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
//
@SecondaryTable(name = "user_details",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private long id;

    String firstName;
    String lastName;
    LocalDate birthdate;
    String email;
    @Column(table = "user_details", unique = true)
    String username;
    @Column(table = "user_details")
    String password;
    @Column(table = "user_details")
    @Enumerated(value = EnumType.STRING)
    Role role;

    public User(@NonNull long id,
                @NonNull String firstName,
                @NonNull String lastName,
                @NonNull LocalDate birthdate,
                @NonNull String email,
                @NonNull String username,
                @NonNull String password,
                @NonNull Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Default
    public User(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

//
