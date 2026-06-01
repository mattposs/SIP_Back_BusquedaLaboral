package com.sip.tp.dto.request;

/**
 * DTO for adding work experience. Consolidated from CandidateController inner class
 */
public record WorkExperienceRequest(
        String company,
        String position,
        String startDate,
        String endDate,
        Boolean isCurrent,
        String description
) {
}

