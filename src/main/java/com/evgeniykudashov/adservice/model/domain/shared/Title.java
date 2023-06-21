package com.evgeniykudashov.adservice.model.domain.shared;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor_ = @Deprecated)
@Embeddable
@Immutable
public class Title {


    @JsonProperty(required = true)
    private String tname;

    @JsonCreator
    public Title(String tname) {
        this.tname = tname;
    }
}
