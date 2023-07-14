package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Schema(
        requiredProperties = Advertisement.DISCRIMINATOR_COLUMN,
        discriminatorProperty = Advertisement.DISCRIMINATOR_COLUMN,
        discriminatorMapping = {
                @DiscriminatorMapping(value = AdvertisementType.Constants.CLOTHING, schema = ClothingAdvertisementRequestDto.class),
                @DiscriminatorMapping(value = AdvertisementType.Constants.SHOE, schema = ShoeAdvertisementRequestDto.class)
        })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = Advertisement.DISCRIMINATOR_COLUMN,
        visible = true)
//        defaultImpl = AdvertisementRequestDto.class)
@JsonSubTypes({
        @JsonSubTypes.Type(name = AdvertisementType.Constants.CLOTHING, value = ClothingAdvertisementRequestDto.class),
        @JsonSubTypes.Type(name = AdvertisementType.Constants.SHOE, value = ShoeAdvertisementRequestDto.class)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementRequestDto implements Serializable {

    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "advertisement id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "advertisement id should be positive")
    })
    private long advertisementId;

    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*() ]+",
            message = "title should contain words or digits or character")
    private String title;

    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*() ]+",
            groups = {CreateConstraint.class, UpdateConstraint.class},
            message = "description should contain words or digits or characters")
    private String description;

    @PositiveOrZero(message = "userId should be minimum 0",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    private long userId;

    @Valid
    private AdvertisementRequestAddressDto address;

    @NotNull(message = "status should not be null",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    private AdvertisementStatus status;

    private long price;

    private AdvertisementType type;
}
