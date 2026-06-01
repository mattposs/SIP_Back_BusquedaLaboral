package com.sip.tp.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for detailed job offer. Consolidated from JobOfferService
 */
public record JobOfferDetailResponse(
        UUID id,
        String title,
        String modality,
        String seniority,
        String description,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        String benefits,
        String location,
        String status,
        Instant createdAt
) {
}

