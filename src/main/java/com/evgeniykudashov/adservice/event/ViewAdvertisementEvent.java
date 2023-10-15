package com.evgeniykudashov.adservice.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.security.Principal;

@Getter
@Setter
public class ViewAdvertisementEvent extends ApplicationEvent {

    private Long advertisementId;

    private Principal principal;

    public ViewAdvertisementEvent(Object source, Long advertisementId, Principal principal) {
        super(source);
        this.advertisementId = advertisementId;
        this.principal = principal;
    }

}
