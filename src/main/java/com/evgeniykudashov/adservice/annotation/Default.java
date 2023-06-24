package com.evgeniykudashov.adservice.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * used by model mapper (mapstruct)
 * Constructor marked @Default annotation will be used for default object creation
 *
 * @see MapStruct documentation
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)
public @interface Default {
}
