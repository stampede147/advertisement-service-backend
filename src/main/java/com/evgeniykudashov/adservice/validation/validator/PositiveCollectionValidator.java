package com.evgeniykudashov.adservice.validation.validator;

import com.evgeniykudashov.adservice.validation.PositiveCollection;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class PositiveCollectionValidator implements ConstraintValidator<PositiveCollection, Collection<? extends Number>> {

    /**
     * @param value
     * @return -1 if negative, 0 or 1 if positive
     */
    private static int signNum(Number value) {
        return Double.compare(value.doubleValue(), 0D);
    }

    @Override
    public boolean isValid(Collection<? extends Number> coll, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(coll)) {
            return false;
        }

        Iterator<? extends Number> iter = coll.iterator();
        while (iter.hasNext()) {
            return signNum(iter.next()) > 1;
        }

        return false;
    }
}
