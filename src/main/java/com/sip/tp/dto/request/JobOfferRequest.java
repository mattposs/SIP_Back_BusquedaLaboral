package com.sip.tp.dto.request;

/**
 * DTO for job offer creation/update. Consolidated from OfferAndMatchController inner class
 */
public record JobOfferRequest(
        String title,
        String modality,
        String seniority,
        String description,
        Double salaryMin,
        Double salaryMax,
        String location,
        String benefits
) {
}

