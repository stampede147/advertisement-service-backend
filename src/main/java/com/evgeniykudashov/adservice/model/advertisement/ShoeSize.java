package com.evgeniykudashov.adservice.model.advertisement;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ShoeSize implements EnumEntity {

    SIZE_36(1, "36"),
    SIZE_37(2, "37"),
    SIZE_38(3, "38"),
    SIZE_39(4, "39"),
    SIZE_40(5, "40"),
    SIZE_41(6, "41"),
    SIZE_42(7, "42"),
    SIZE_43(8, "43"),
    SIZE_44(9, "44"),
    SIZE_45(10, "45"),
    SIZE_46(11, "46");

    final int id;

    final String label;

    @Override
    @JsonCreator
    public EnumEntity valueForId(int id) {
        return Utility.valueForId(id);
    }

    @UtilityClass
    static class Utility {

        private static final List<ShoeSize> cache = Arrays.asList(ShoeSize.class.getEnumConstants());

        public static ShoeSize valueForId(int id) {

            return cache.stream()
                    .filter(shoeSize -> shoeSize.id == id)
                    .findFirst().orElseThrow(() -> new RuntimeException("not found value with provided parameter size: " + id));
        }
    }
}
