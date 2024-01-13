package com.evgeniykudashov.adservice.model.advertisement;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ClothingSize implements EnumEntity {

    XXS(1, 38, "XXS"),
    XS(2, 40, "XS"),
    S(3, 42, "S"),
    S_M(4, 44, "S/M"),
    M(5, 46, "M"),
    L(6, 48, "L"),
    L_XL(7, 50, "L_XL"),
    XL(8, 52, "XL"),
    XXL(9, 54, "XXL");

    private static final ClothingSize[] cached = ClothingSize.values();
    int id;
    int traditionalSize;
    String internationalSize;
    String label;

    ClothingSize(int id, int traditionalSize, String size) {
        this.id = id;
        this.traditionalSize = traditionalSize;
        this.internationalSize = size;
        this.label = String.format("%d (%s)", this.traditionalSize, this.internationalSize);
    }

    @JsonCreator
    @Override
    public EnumEntity valueForId(int id) {
        return Arrays.stream(ClothingSize.cached)
                .filter(size -> size.id == id)
                .findAny().orElse(null);
    }

}
