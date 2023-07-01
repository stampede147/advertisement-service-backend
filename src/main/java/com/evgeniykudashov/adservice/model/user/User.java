package com.evgeniykudashov.adservice.model.user;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Builder
@Getter
@Setter
//
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private long id;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(long id,
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
}

//
