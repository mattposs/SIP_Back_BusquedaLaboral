package com.sip.tp.dto.skill;

import java.util.UUID;

public record CandidateSkillResponse(
        UUID id,
        UUID skillId,
        String skillName,
        String skillType,
        String experienceRange,
        String consolidatedLevel,
        Double consolidatedScore
) {
}
