package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class ChatResponseDto {

    long id;

    ChatResponseAdvertisementDto advertisement;

    Set<ChatResponseUserDto> participants;

}
