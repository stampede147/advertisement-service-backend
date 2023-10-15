package com.evgeniykudashov.adservice.model.chat;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(onConstructor_ = @Deprecated)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
//
@Entity
@Table(name = "chats")
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", updatable = false)
    @Immutable
    private Advertisement advertisement;

    @OneToOne
    @JoinColumn(name = "user_buyer_id")
    private User buyer;

    @Enumerated(EnumType.STRING)
    private ChatStatus status;

    private LocalDate createdAt;

    public Chat(long id,
                @NonNull Advertisement advertisement,
                @NonNull User buyer,
                @NonNull LocalDate createdAt) {
        this.id = id;
        this.advertisement = advertisement;
        this.buyer = buyer;
        this.createdAt = createdAt;
    }
}

