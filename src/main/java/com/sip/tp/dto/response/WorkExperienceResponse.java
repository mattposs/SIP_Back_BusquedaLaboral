package com.sip.tp.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record WorkExperienceResponse(
        UUID id,
        String company,
        String position,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isCurrent,
        String description
) {
}
