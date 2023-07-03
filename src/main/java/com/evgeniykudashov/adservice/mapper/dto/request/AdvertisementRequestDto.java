import com.evgeniykudashov.adservice.mapper.dto.AddressDto;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementRequestDto implements Serializable {

    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "advertisement id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "advertisement id should be positive")
    })
    private long advertisementId;

    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*() ]+", message = "title should contain words or digits or character")
    private String title;

    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*() ]+", message = "description should contain words or digits or characters")
    private String description;

    @PositiveOrZero(message = "userId should be minimum 0")
    private long userId;

    @Valid
    private AddressDto address;

    @NotNull(message = "status should not be null")
    private AdvertisementStatus status;
}
