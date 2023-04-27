package com.evgeniykudashov.adservice.model.domain.aggregate.user;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.Fullname;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.UserSecretDetails;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private Fullname fullname;
    private UserSecretDetails secretDetails;

    @ElementCollection
    @CollectionTable(name = "users_feedbacks",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Feedback> feedbacks;

    public User(Fullname fullname, UserSecretDetails secretDetails) {
        this.fullname = fullname;
        this.secretDetails = secretDetails;
    }

    public void changeFullName(Fullname fullname) {
        this.fullname = fullname;
    }

    public void changeSecretDetails(UserSecretDetails secretDetails) {
        this.secretDetails = secretDetails;
    }


    public void addFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
    }

    public void removeFeedbackByIndex(int index) {
        this.feedbacks.remove(index);
    }
}
