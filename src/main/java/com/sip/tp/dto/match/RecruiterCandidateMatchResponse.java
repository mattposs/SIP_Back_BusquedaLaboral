package com.sip.tp.dto.match;

import java.util.List;
import java.util.UUID;

/**
 * DTO for recruiter's view of candidate match. Consolidated from MatchFlowService
 */
public record RecruiterCandidateMatchResponse(
        UUID matchId,
        UUID candidateId,
        UUID offerId,
        Integer matchScore,
        Boolean profileRevealed,
        String candidateName,
        String currentRole,
        String location,
        Boolean identityVerified,
        List<String> skills,
        String email,
        String linkedIn
) {
}

