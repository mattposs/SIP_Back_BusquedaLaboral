package com.sip.tp.dto.match;

import java.util.UUID;

/**
 * DTO for candidate matches. Consolidated from MatchFlowService
 */
public record CandidateMatchResponse(
        UUID offerId,
        String offerTitle,
        String companyName,
        Integer matchScore,
        String status
) {
}

