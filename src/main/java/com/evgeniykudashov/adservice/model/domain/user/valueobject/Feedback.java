package com.evgeniykudashov.adservice.model.domain.user.valueobject;

import com.evgeniykudashov.adservice.model.domain.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.user.User;
import com.evgeniykudashov.adservice.model.domain.user.status.Mark;
import com.evgeniykudashov.adservice.model.shared.Description;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Immutable
@Embeddable
public class Feedback {

    @OneToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @OneToOne
    @JoinColumn(name = "user_sender_id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "user_sender_id")
    private User recipient;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    private Description description;

    public Feedback(Advertisement advertisement,
                    User sender,
                    User recipient,
                    Mark mark,
                    Description description) {
        this.advertisement = advertisement;
        this.sender = sender;
        this.recipient = recipient;
        this.mark = mark;
        this.description = description;
    }
}
