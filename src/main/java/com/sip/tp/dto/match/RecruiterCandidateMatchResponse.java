package com.sip.tp.dto.match;

import java.util.UUID;

/**
 * DTO for recruiter's view of candidate match. Consolidated from MatchFlowService
 */
public record RecruiterCandidateMatchResponse(
        UUID matchId,
        Integer matchScore,
        Boolean profileRevealed,
        String candidateName,
        String email,
        String linkedIn
) {
}

