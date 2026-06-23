package com.sip.tp.dto.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Cross-field constraint that ensures {@code endDate} is present when {@code isCurrent} is false.
 */
@Documented
@Constraint(validatedBy = WorkExperienceDatesValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWorkExperienceDates {

    String message() default "endDate is required when isCurrent is false";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

