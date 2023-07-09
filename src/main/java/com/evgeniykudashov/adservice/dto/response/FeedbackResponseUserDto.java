package com.evgeniykudashov.adservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackResponseUserDto {

    private long userId;

    private String firstName;

    private String lastName;
}
