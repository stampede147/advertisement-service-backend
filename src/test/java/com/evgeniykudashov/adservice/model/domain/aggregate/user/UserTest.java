package com.evgeniykudashov.adservice.model.domain.aggregate.user;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserTest {


    @InjectMocks
    User sut;

    public void can_update_personal_details() {
        PersonalDetails personalDetails = TestValues.getPersonalDetailsObject();

        sut.updatePersonalDetails(personalDetails);

        Assertions.assertEquals(personalDetails, sut.getPersonalDetails());
    }

    public void can_update_access_details() {
        AccessDetails accessDetails = TestValues.getAccessDetailsObject();

        sut.updateAccessDetails(accessDetails);

        Assertions.assertEquals(accessDetails, sut.getAccessDetails());
    }
}