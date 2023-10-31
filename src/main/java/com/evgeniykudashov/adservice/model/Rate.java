package com.evgeniykudashov.adservice.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rate {

    ZERO, ONE, TWO, THREE, FOUR, FIVE;

    @JsonValue
    public int getValue() {
        return this.ordinal();
    }
}
