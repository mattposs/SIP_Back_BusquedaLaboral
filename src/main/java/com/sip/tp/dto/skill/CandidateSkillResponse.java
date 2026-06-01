package com.sip.tp.dto.skill;

import java.util.UUID;

/**
 * DTO for candidate skills. Consolidated from SkillService
 */
public record CandidateSkillResponse(
        UUID id,
        String skillName,
        String experienceRange,
        String consolidatedLevel,
        Double consolidatedScore
) {
}

