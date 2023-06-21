package com.evgeniykudashov.adservice.helper;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class StringUtils {

    public String capitalize(String str) {
        Objects.requireNonNull(str);
        return str.substring(0, 1).concat(str.substring(1));
    }

    public String concat(String str1, String str2) {
        return str1.concat(str2);
    }
}
