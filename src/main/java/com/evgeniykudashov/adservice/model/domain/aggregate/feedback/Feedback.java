package com.evgeniykudashov.adservice.model.domain.aggregate.feedback;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    @EqualsAndHashCode.Include
    @Getter
    public long id;

    @OneToOne
    @JoinColumn(name = "advertisement_id")
    @Immutable
    private Advertisement advertisement;

    @OneToOne
    @JoinColumn(name = "sender_user_id")
    @Immutable
    private User sender;

    @OneToOne
    @JoinColumn(name = "recipient_user_id")
    @Immutable
    private User recipient;

    @Enumerated(EnumType.ORDINAL)
    @Getter
    private Mark mark;

    @Getter
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

    public void updateMark(Mark mark) {
        this.mark = mark;
    }

    public void updateDescription(Description description) {
        this.description = description;
    }
}
