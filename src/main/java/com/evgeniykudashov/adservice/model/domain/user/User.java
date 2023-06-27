package com.evgeniykudashov.adservice.model.domain.user;


import com.evgeniykudashov.adservice.annotation.Default;
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

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;


    @Column(table = "user_details")
    private UserDetails userDetails;


    public User(@NonNull long id,
                @NonNull String firstName,
                @NonNull String lastName,
                @NonNull LocalDate birthdate,
                @NonNull String email,
                @NonNull UserDetails userDetails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.userDetails = userDetails;
    }

    @Default
    public User(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

//
