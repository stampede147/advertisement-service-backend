package com.evgeniykudashov.adservice.model.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class UserDetails {

    @Column(unique = true)
//    @Column(table = "user_details", unique = true)
    private String username;

    //    @Column(table = "user_details")
    private String password;

    //    @Column(table = "user_details")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public UserDetails(@NonNull String username, @NonNull String password, @NonNull Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
