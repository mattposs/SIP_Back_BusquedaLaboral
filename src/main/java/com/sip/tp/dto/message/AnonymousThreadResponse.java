package com.sip.tp.dto.message;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO for anonymous thread summary. Consolidated from AnonymousInteractionService
 */
public record AnonymousThreadResponse(
        UUID id,
        String anonymousCode,
        String category,
        String status,
        UUID offerId,
        Instant createdAt
) {
}

