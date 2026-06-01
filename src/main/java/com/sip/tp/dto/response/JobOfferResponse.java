package com.sip.tp.dto.response;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for job offers. Consolidated from JobOfferService
 */
public record JobOfferResponse(
        UUID id,
        String title,
        String modality,
        String seniority,
        String status,
        String location,
        Instant createdAt
) {
}

