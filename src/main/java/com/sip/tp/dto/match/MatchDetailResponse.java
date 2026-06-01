package com.sip.tp.dto.match;

import java.util.UUID;

/**
 * DTO for match details. Consolidated from MatchFlowService
 */
public record MatchDetailResponse(
        UUID offerId,
        String offerTitle,
        String companyName,
        Integer matchScore,
        String status,
        Boolean profileRevealed
) {
}

