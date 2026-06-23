package com.sip.tp.dto.request.validation;

import com.sip.tp.dto.request.WorkExperienceRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validates that {@code endDate} is present when {@code isCurrent} is null or false.
 * If {@code isCurrent} is true, {@code endDate} is not required.
 */
public class WorkExperienceDatesValidator implements ConstraintValidator<ValidWorkExperienceDates, WorkExperienceRequest> {

    @Override
    public boolean isValid(WorkExperienceRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true; // null request is handled by @NotNull elsewhere
        }

        Boolean isCurrent = request.isCurrent();

        // If isCurrent is true, endDate is not required
        if (Boolean.TRUE.equals(isCurrent)) {
            return true;
        }

        // If isCurrent is null or false, endDate must be present
        String endDate = request.endDate();
        if (endDate == null || endDate.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("endDate is required when isCurrent is false")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

