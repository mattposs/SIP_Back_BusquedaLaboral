package com.sip.tp.dto.request;

import com.sip.tp.dto.request.validation.ValidWorkExperienceDates;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for adding work experience. Consolidated from CandidateController inner class
 */
@ValidWorkExperienceDates
public record WorkExperienceRequest(
        @NotBlank(message = "Company is required")
        String company,

        @NotBlank(message = "Position is required")
        String position,

        @NotBlank(message = "Start date is required")
        String startDate,

        String endDate,

        @NotNull(message = "isCurrent is required")
        Boolean isCurrent,

        String description
) {
}

