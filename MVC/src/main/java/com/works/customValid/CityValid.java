package com.works.customValid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {CityValidService.class})
public @interface CityValid {

    String message() default "City Not Valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
