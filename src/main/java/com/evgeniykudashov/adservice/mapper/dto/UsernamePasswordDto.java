package com.evgeniykudashov.adservice.mapper.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsernamePasswordDto {

    public String username;

    public String password;

    public UsernamePasswordDto(@JsonProperty(required = true) String username,
                               @JsonProperty(required = true) String password) {
        this.username = username;
        this.password = password;
    }
}
