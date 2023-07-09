package com.evgeniykudashov.adservice.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FeedbackStatus {

    DEAL_AGREED("Deal agreed"), DEAL_NOT_AGREED("Deal not agreed");

    @Getter
    private String val;
}
