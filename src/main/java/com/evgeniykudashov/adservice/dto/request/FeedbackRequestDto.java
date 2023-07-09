package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.feedback.FeedbackStatus;
import com.evgeniykudashov.adservice.model.feedback.Mark;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackRequestDto {

    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "chat id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "chat id should be positive")
    })
    private long feedbackId;

    @NotEmpty(groups = {CreateConstraint.class, UpdateConstraint.class})
    private String description;

    @Positive(message = "advertisement id should be positive", groups = {CreateConstraint.class, UpdateConstraint.class})
    private long advertisementId;

    @Positive(message = "seller id should be positive", groups = {CreateConstraint.class, UpdateConstraint.class})
    private long sellerId;

    @Positive(message = "customer id should be positive", groups = {CreateConstraint.class, UpdateConstraint.class})
    private long customerId;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    @Enumerated(value = EnumType.STRING)
    private Mark mark;

    @NotNull(groups = {CreateConstraint.class, UpdateConstraint.class})
    @Enumerated(value = EnumType.STRING)
    private FeedbackStatus status;
}
