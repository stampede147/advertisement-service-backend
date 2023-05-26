package com.evgeniykudashov.adservice;


import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.MessageBody;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.PublicationDateTime;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.Feedback;
import com.evgeniykudashov.adservice.model.domain.aggregate.feedback.status.Mark;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.Fullname;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.PersonalDetails;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.model.domain.shared.security.Password;
import com.evgeniykudashov.adservice.model.domain.shared.security.Username;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class TestValues {

    public static final Long ID = 1L;

    public Description getDescriptionObject() {
        return new Description("description");
    }


    public Address getAddressObject() {
        return new Address(105155, "a", "b", "c");
    }

    public Title getTitleObject() {
        return new Title("title");
    }


    public Category getCategoryObject() {
        return new Category(getTitleObject(), null, null);
    }

    public MessageBody getMessageBodyObject() {
        return new MessageBody("Message_body");
    }


    public PublicationDateTime getPublicationDateTimeObject() {
        return new PublicationDateTime(LocalDateTime.now());
    }

    public ChatMessage getChatMessageObject() {
        return new ChatMessage(getMessageBodyObject(),
                getUserObject(),
                getPublicationDateTimeObject());
    }

    public User getUserObject() {
        return new User(getPersonalDetailsObject(),
                getAccessDetailsObject());
    }

    public PersonalDetails getPersonalDetailsObject() {
        return new PersonalDetails(new Fullname("full", "name"));
    }

    public AccessDetails getAccessDetailsObject() {
        return new AccessDetails(new Username("username"), new Password("password"));
    }

    public Advertisement getAdvertisementObject() {
        return new Advertisement(getTitleObject(),
                getDescriptionObject(),
                getCategoryObject(),
                getUserObject(),
                getAddressObject()
        );
    }

    public Chat getChatObject() {
        return new Chat(getAdvertisementObject(), List.of(getUserObject()));
    }

    public Feedback getFeedbackObject() {
        return new Feedback(getAdvertisementObject(),
                getUserObject(),
                getUserObject(),
                Mark.FIVE,
                getDescriptionObject());
    }

}
