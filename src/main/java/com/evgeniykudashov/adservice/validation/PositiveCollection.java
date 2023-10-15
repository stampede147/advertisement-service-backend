package com.evgeniykudashov.adservice.validation;

import com.evgeniykudashov.adservice.validation.validator.PositiveCollectionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositiveCollectionValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveCollection {
    String message() default "value in collection must be greater than 0";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};


}
