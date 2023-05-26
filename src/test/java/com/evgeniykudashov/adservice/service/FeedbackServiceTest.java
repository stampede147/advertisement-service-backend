package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.repository.FeedbackRepository;
import com.evgeniykudashov.adservice.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackServiceImpl sut;

    @Test
    void create() {
        Feedback feedback = TestValues.getFeedbackObject();
        Mockito.when(feedbackRepository.save(Mockito.any(Feedback.class))).thenReturn(feedback);

        sut.create(feedback);

        Mockito.verify(feedbackRepository).save(Mockito.any(Feedback.class));
    }

    @Test
    void remove() {
        Feedback feedback = TestValues.getFeedbackObject();

        sut.remove(feedback.getId());

        Mockito.verify(feedbackRepository).deleteById(Mockito.any(Long.class));
    }

    @Test
    void updateMark() {
        Feedback feedback = TestValues.getFeedbackObject();
        Mark mark = Mark.FOUR;
        Mockito.when(feedbackRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(feedback));

        sut.updateMark(mark, feedback.getId());

        Mockito.verify(feedbackRepository).save(feedback);

    }

    @Test
    void updateDescription() {
        Feedback feedback = TestValues.getFeedbackObject();
        Description description = TestValues.getDescriptionObject();
        Mockito.when(feedbackRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(feedback));

        sut.updateDescription(description, feedback.getId());

        Mockito.verify(feedbackRepository).save(feedback);
    }

    @Test
    void findAllByRecipientUserId() {
        Long ID = 0L;

        sut.findAllByRecipientUserId(ID);

        Mockito.verify(feedbackRepository).findAllByRecipientId(Mockito.any(Long.class));
    }

    @Test
    void findAllBySenderUserId() {
        Long ID = 0L;

        sut.findAllBySenderUserId(ID);

        Mockito.verify(feedbackRepository).findAllBySenderId(Mockito.any(Long.class));
    }
}