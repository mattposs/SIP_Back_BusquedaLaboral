package com.sip.tp.dto.match;

import java.util.List;
import java.util.UUID;

public record MatchDetailResponse(
        UUID offerId,
        String offerTitle,
        String companyName,
        Integer matchScore,
        String status,
        Boolean profileRevealed,
        String description,
        List<MatchingSkillResponse> matchingSkills,
        List<MissingSkillResponse> missingSkills
) {
}
