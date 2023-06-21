package com.evgeniykudashov.adservice.model.domain.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Immutable;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
public class Description {


    @JsonProperty(value = "dname", required = true)
    private String dname;

    @JsonCreator
    public Description(@NonNull String dname) {
        this.dname = dname;
    }
}
