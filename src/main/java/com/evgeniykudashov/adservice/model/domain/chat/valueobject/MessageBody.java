package com.evgeniykudashov.adservice.model.domain.chat.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Embeddable
public class MessageBody implements Serializable {

    private String data;

}
