package com.evgeniykudashov.adservice.model.domain.aggregate.feedback;


import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedbackTest {

    @InjectMocks
    Feedback sut;

    @Test
    void can_update_mark() {
        Mark mark = Mark.FIVE;

        sut.updateMark(mark);

        Assertions.assertEquals(mark, sut.getMark());
    }

    void can_update_description() {
        Description descriptionObject = TestValues.getDescriptionObject();

        sut.updateDescription(descriptionObject);

        Assertions.assertEquals(descriptionObject, sut.getDescription());
    }
}