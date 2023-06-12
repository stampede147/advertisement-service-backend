package com.evgeniykudashov.adservice.controller.service.helper;


import lombok.experimental.UtilityClass;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;


@UtilityClass
public class ReflectionUtility {


    /**
     * Calls method of target object
     *
     * @param target     - concrete object
     * @param methodName - method name
     * @param args       - arguments to be passed to method
     * @throws IllegalArgumentException - throws exception if method was not found
     */
    public void callMethod(Object target, String methodName, Object... args) throws IllegalArgumentException {
        Objects.requireNonNull(target);

        Method method = ReflectionUtils.findMethod(target.getClass(), methodName);
        if (Objects.nonNull(method)) {
            ReflectionUtils.invokeMethod(method, target, args);
        } else {
            throw new IllegalArgumentException("not found such method to call");
        }
    }
}
