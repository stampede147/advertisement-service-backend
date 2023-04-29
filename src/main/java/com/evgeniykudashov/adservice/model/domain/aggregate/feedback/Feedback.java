package com.evgeniykudashov.adservice.model.domain.aggregate.feedback;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.entity.User;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
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
    @JoinColumn(name = "sender_user_id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "recipient_user_id")
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
