package com.sip.tp.dto.message;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for anonymous thread detail. Consolidated from AnonymousInteractionService
 */
public record AnonymousThreadDetailResponse(
        UUID id,
        String anonymousCode,
        String category,
        String status,
        UUID offerId,
        String offerTitle,
        String companyName,
        List<AnonymousMessageResponse> messages,
        Instant createdAt
) {
    public record AnonymousMessageResponse(
            UUID id,
            String senderType,
            String content,
            Instant createdAt
    ) {
    }
}

